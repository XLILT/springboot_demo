package com.sunlands.addTinyPic.mapper;

import com.sunlands.addTinyPic.entity.Img;
import com.sunlands.addTinyPic.entity.RoomImg;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PicMapper {
    @Select("SELECT PrimaryKey,RoomId,DocId,PageId,IsComplete,IsThumbnail FROM sunlive.roomimg WHERE RoomId=#{rid} AND DocId=#{docid} AND PageId=#{pageid} AND IsThumbnail=0")
    public RoomImg getRoomImage(@Param("rid") Long rid, @Param("docid") Long docid, @Param("pageid") Long pageid);

    @Select("SELECT * FROM sunlive.img WHERE Id=#{id}")
    public Img getImage(Long id);

    @Options(useGeneratedKeys = true, keyProperty = "Id")
    @Insert("INSERT INTO sunlive.img(ImgUrl, ImgType, ImgSize, ImgWidth, ImgHeight, ImgHash, Source) VALUES(#{ImgUrl},#{ImgType},#{ImgSize},#{ImgWidth},#{ImgHeight},#{ImgHash},#{Source})")
    public int putImage(Img img);

    @Insert("INSERT INTO sunlive.roomimg(PrimaryKey,RoomId,DocId,PageId,IsComplete,IsThumbnail) VALUES(#{PrimaryKey},#{RoomId},#{DocId},#{PageId},#{IsComplete},#{IsThumbnail})")
    public int putRoomImage(RoomImg roomImg);
}
