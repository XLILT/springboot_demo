package com.sunlands.addTinyPic.web.controller;

import com.sunlands.addTinyPic.common.SFSKeyGenerator;
import com.sunlands.addTinyPic.entity.Img;
import com.sunlands.addTinyPic.entity.RoomImg;
import com.sunlands.addTinyPic.service.ConvertImgService;
import com.sunlands.addTinyPic.service.FileMetaService;
import com.sunlands.addTinyPic.service.PicMetaService;
import com.sunlands.addTinyPic.service.SFSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class AddTinyPicController {
    @Autowired
    private PicMetaService picMetaService;

    @Autowired
    private SFSService sfsService;

    @Autowired
    private ConvertImgService convertImgService;

    @Autowired
    private FileMetaService fileMetaService;

    @GetMapping("/addTiny")
    public int addTiny(@RequestParam Long rid, @RequestParam Long docid, @RequestParam Long pageid) {
        log.info("{}, {}, {}", rid, docid, pageid);
        Img img = picMetaService.getRoomImg(rid, docid, pageid);
        log.info("{}", img);

        String key = img.getImgUrl();

        String rowFileName = SFSKeyGenerator.getFileNameFromKey(key);

        int ret = sfsService.downloadImage(key, rowFileName);
        if(ret != 0) {
            return ret;
        }

        log.info("download {} succ", key);

        String jpgKey = SFSKeyGenerator.genJpgKey(rowFileName);

        ret = convertImgService.convert(rowFileName, 0, 0,jpgKey);
        if(ret != 0) {
            return ret;
        }

        log.info("convert jpg {} succ", jpgKey);

        String jpgHash = fileMetaService.getMD5(jpgKey);

        if(jpgHash == null) {
            log.error("get md5 failed {}", jpgKey);

            return -1;
        }

        Img jpgImg = picMetaService.getImageByHash(jpgHash);

        if(jpgImg == null) {
            jpgImg = new Img();

            jpgImg.setImgUrl(jpgKey);
            jpgImg.setImgType(3L);
            jpgImg.setImgWidth(img.getImgWidth());
            jpgImg.setImgHeight(img.getImgHeight());
            jpgImg.setImgHash(jpgHash);
            jpgImg.setSource(1L);

            ret = sfsService.uploadImage(jpgKey, jpgKey, jpgImg);

            if(ret != 0) {
                return ret;
            }

            log.info("upload jpg {} succ", jpgKey);

            log.info("{}", jpgImg);

            ret = picMetaService.putImage(jpgImg);

            if(ret != 1) {
                log.error("put img failed {}", jpgImg);
                return -1;
            }

            log.info("put jpg img succ {}", jpgImg);
        }

        RoomImg jpgRoomImg = new RoomImg();
        jpgRoomImg.setPrimaryKey(jpgImg.getId());
        jpgRoomImg.setRoomId(rid);
        jpgRoomImg.setDocId(docid);
        jpgRoomImg.setPageId(pageid);
        jpgRoomImg.setIsComplete(1L);
        jpgRoomImg.setIsThumbnail(2L);

        log.info("{}", jpgRoomImg);

        ret = picMetaService.putRoomImage(jpgRoomImg);

        if(ret != 1) {
            log.error("put roomimg failed {}", jpgRoomImg);
            return -1;
        }

        log.info("put jpg roomimg succ {}", jpgRoomImg);

        String tinyKey = SFSKeyGenerator.genTinyKey(rowFileName);

        ret = convertImgService.convert(rowFileName, 640, 480, tinyKey);
        if(ret != 0) {
            return ret;
        }

        log.info(" convert tiny {} succ", tinyKey);

        String tinyHash = fileMetaService.getMD5(tinyKey);

        if(tinyHash == null) {
            log.error("get md5 failed {}", tinyKey);

            return -1;
        }

        Img tinyImg = picMetaService.getImageByHash(tinyHash);

        if (tinyImg == null) {
            tinyImg = new Img();

            tinyImg.setImgUrl(tinyKey);
            tinyImg.setImgType(1L);
            tinyImg.setImgWidth(640L);
            tinyImg.setImgHeight(480L);
            tinyImg.setImgHash(tinyHash);
            tinyImg.setSource(1L);

            ret = sfsService.uploadImage(tinyKey, tinyKey, tinyImg);
            if(ret != 0) {
                return ret;
            }

            log.info("upload tiny {} succ", tinyKey);

            log.info("{}", tinyImg);

            ret = picMetaService.putImage(tinyImg);

            if(ret != 1) {
                log.error("put img failed {}", tinyImg);
                return -1;
            }

            log.info("put tiny img succ {}", tinyImg);
        }

        RoomImg tinyRoomImg = new RoomImg();
        tinyRoomImg.setPrimaryKey(tinyImg.getId());
        tinyRoomImg.setRoomId(rid);
        tinyRoomImg.setDocId(docid);
        tinyRoomImg.setPageId(pageid);
        tinyRoomImg.setIsComplete(1L);
        tinyRoomImg.setIsThumbnail(1L);

        log.info("{}", tinyRoomImg);

        ret = picMetaService.putRoomImage(tinyRoomImg);

        if(ret != 1) {
            log.error("put roomimg failed {}", tinyRoomImg);
            return -1;
        }

        log.info("put tiny roomimg succ {}", tinyRoomImg);

        log.info("key {} deal over", key);

        return 0;
    }

    @GetMapping("/download")
    public int addTiny(@RequestParam String key) {
        String rowFileName = SFSKeyGenerator.getFileNameFromKey(key);

        int ret = sfsService.downloadImage(key, rowFileName);
        if(ret != 0) {
            return ret;
        }

        log.info("download {} as {}", key, rowFileName);

        return 0;
    }
}
