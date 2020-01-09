package com.sunlands.addTinyPic.service;

import com.sunlands.addTinyPic.common.SFSKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;

@Slf4j
@Service
public class ConvertImgService {
    public int convert(String rowImg, int w, int h, String outImg) {
        String[] cmd = null;
        if(w == 0 || h == 0) {
            cmd = new String[]{"convert", rowImg, outImg};
        } else {
            cmd = new String[]{"convert", rowImg, "-resize", "" + w + "x" + h, outImg};
        }

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

                return -1;
            }
        } catch (IOException e) {
            log.error("{}", e);
            return -1;
        } catch (InterruptedException e) {
            log.error("{}", e);
            return -1;
        }

        return 0;
    }
}
