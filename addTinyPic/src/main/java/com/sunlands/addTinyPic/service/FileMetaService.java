package com.sunlands.addTinyPic.service;

import com.sunlands.addTinyPic.common.SFSKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;

@Slf4j
@Service
public class FileMetaService {
    public String getMD5(String fname) {
        String md5 = null;

        String[] cmd = new String[]{"md5sum", fname};

        log.info("{}", Arrays.toString(cmd));

        try {
            Process process = Runtime.getRuntime().exec(cmd, null, new File(SFSKeyGenerator.IMG_DIR));

            InputStream sis = process.getInputStream();
            BufferedReader sbr = new BufferedReader(new InputStreamReader(sis));

            StringBuilder ssb = new StringBuilder();

            String str = sbr.readLine();
            while(str != null) {
                ssb.append(str);

                str = sbr.readLine();
            }

            log.info("std {}", ssb.toString());

            String[] split = ssb.toString().split(" ");
            if(split.length < 1) {
                log.error("{}", Arrays.toString(split));

                return null;
            }

            md5 = split[0];

            InputStream eis = process.getErrorStream();
            BufferedReader ebr = new BufferedReader(new InputStreamReader(sis));

            ssb = new StringBuilder();
            str = ebr.readLine();
            while(str != null) {
                ssb.append(str);

                str = ebr.readLine();
            }

            log.info("err {}", ssb.toString());

            int ret = process.waitFor();
            if(ret != 0) {
                log.error("waitFor ret {}", ret);

                return null;
            }
        } catch (IOException e) {
            log.error("{}", e);
            return null;
        } catch (InterruptedException e) {
            log.error("{}", e);
            return null;
        }

        return md5;
    }
}
