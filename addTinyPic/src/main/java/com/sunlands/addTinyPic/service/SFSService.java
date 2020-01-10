package com.sunlands.addTinyPic.service;

import com.alibaba.fastjson.JSONObject;
import com.sunlands.addTinyPic.common.SFSKeyGenerator;
import com.sunlands.addTinyPic.entity.Img;
import com.sunlands.addTinyPic.entity.SFSConf;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
@Service
public class SFSService {
    @Autowired
    private SFSConf sfsConf;

    private static String version = "1.0";
    private static String token = null;
    private static long last_token_ts = 0;

    private String fname = null;

    private String getToken() {
        String accessKey = sfsConf.getAccessKey();
        String secretKey = sfsConf.getSecretKey();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessKey", accessKey);
        jsonObject.put("secretKey", secretKey);
        //token有效时长，默认1小时
        jsonObject.put("timeout", 60*60);

        OkHttpClient client = new OkHttpClient();
        MediaType type = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(type, jsonObject.toJSONString());

        Request request = new Request.Builder()
                .addHeader("version", version)
                .url(sfsConf.getDomain() + sfsConf.getTokenURL())
                .post(body)
                .build();

        Call call = client.newCall(request);

        JSONObject tokenJson = null;

        try {
            Response execute = call.execute();
            String tokenRes = Objects.requireNonNull(execute.body()).string();
            tokenJson = JSONObject.parseObject(tokenRes);
        } catch(IOException e) {
            log.error("{}", e);

            return null;
        }

        token = tokenJson.getJSONObject("data").getString("accessToken");
        last_token_ts = System.currentTimeMillis();

        return token;
    }

    public int downloadImage(String key, String fname) {
        if (token == null || last_token_ts < System.currentTimeMillis() - 3500000L) {
            getToken();
        }

        if (token == null) {
            log.error("get token failed.");
            return -1;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", key);

        OkHttpClient client = new OkHttpClient();
        MediaType type = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(type, jsonObject.toJSONString());
        Request request = new Request.Builder()
                .addHeader("version", version)
                .addHeader("authorization", token)
                .url(sfsConf.getDomain() + sfsConf.getDownloadURL())
                .post(body)
                .build();

        Call call = client.newCall(request);

        JSONObject retObj = null;

        try {
            Response execute = call.execute();
            String retStr = Objects.requireNonNull(execute.body()).string();

            retObj = JSONObject.parseObject(retStr);
        } catch (IOException e) {
            log.error("{}", e);
            return -1;
        }

        log.info("{}", retObj);

        Request downloadRequest = new Request.Builder()
                .url(retObj.getString("data"))
                .build();

        Call downloadCall = client.newCall(downloadRequest);

        try {
            Response response = downloadCall.execute();

            InputStream is = null;
            byte[] buf = new byte[2048];
            int len = 0;
            FileOutputStream fos = null;

            File dir = new File(SFSKeyGenerator.IMG_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, fname);

            is = response.body().byteStream();

            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }

            fos.flush();

            log.info("download {} succ", fname);

        } catch (IOException e) {
            log.error("{}", e);
            return -1;
        }

        return 0;
    }

    public int uploadImage(String key, String fname, Img img) {
        if (token == null || last_token_ts < System.currentTimeMillis() - 3500000L) {
            getToken();
        }

        if (token == null) {
            log.error("get token failed.");
            return -1;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key",key);

        MediaType type = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(type, jsonObject.toJSONString());
        Request request = new Request.Builder()
                .addHeader("version", version)
                .addHeader("authorization", token)
                .url(sfsConf.getDomain() + sfsConf.getUploadURL())
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();

        Call call = client.newCall(request);

        Response execute = null;
        String uploadParam = null;

        try {
            execute = call.execute();
            uploadParam = execute.body().string();
        } catch (IOException e) {
            log.error("{}", e);

            return -1;
        }

        JSONObject uploadJson = JSONObject.parseObject(uploadParam);

        File file = new File(SFSKeyGenerator.IMG_DIR + "/" + fname);

        img.setImgSize(file.length());

        RequestBody uploadBody = RequestBody.create(null, file);
        Request.Builder requestBuilder = new Request.Builder()
                .url(uploadJson.getJSONObject("data").getString("uploadUrl"))
                //注意：上传必须是put请求
                .put(uploadBody);

        uploadJson.getJSONObject("data").getJSONObject("header").forEach((k,v)-> requestBuilder.addHeader(k , v.toString()));

        try {
            execute = client.newCall(requestBuilder.build()).execute();
        } catch (IOException e) {
            log.error("{}", e);
            return -1;
        }



        if( ! execute.isSuccessful()){
            try {
                String errorXml = Objects.requireNonNull(execute.body()).string();
            } catch (IOException e) {
                log.error("{}", e);

                return -1;
            }
        }

        log.info("upload succ {} {}", key, fname);

        return 0;
    }
}
