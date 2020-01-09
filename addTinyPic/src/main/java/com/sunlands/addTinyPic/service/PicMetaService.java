package com.sunlands.addTinyPic.service;

import com.sunlands.addTinyPic.entity.Img;
import com.sunlands.addTinyPic.entity.RoomImg;
import com.sunlands.addTinyPic.mapper.PicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PicMetaService {
    @Autowired
    private PicMapper picMapper;

    @Autowired
    public PicMetaService(PicMapper picMapper) { this.picMapper = picMapper; }

    public Img getRoomImg(Long rid, Long docid, Long pageid) {
        RoomImg rimg =  picMapper.getRoomImage(rid, docid, pageid);

        if(rimg == null) {
            return null;
        }

        return picMapper.getImage(rimg.getPrimaryKey());
    }

    public int putImage(Img img) {
        return picMapper.putImage(img);
    }

    public int putRoomImage(RoomImg roomImg) {
        return picMapper.putRoomImage(roomImg);
    }

}
