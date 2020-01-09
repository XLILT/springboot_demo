package com.sunlands.addTinyPic.entity;

import lombok.Data;

@Data
public class Img {
    private Long Id;
    private String ImgUrl;
    private Long ImgType;
    private Long ImgSize;
    private Long ImgWidth;
    private Long ImgHeight;
    private String ImgHash;
    private Long Source;
}
