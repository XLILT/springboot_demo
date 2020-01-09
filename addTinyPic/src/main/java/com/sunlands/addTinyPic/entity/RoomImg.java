package com.sunlands.addTinyPic.entity;

import lombok.Data;

@Data
public class RoomImg {
    private Long PrimaryKey;
    private Long RoomId;
    private Long DocId;
    private Long PageId;
    private Long IsComplete;
    private Long IsThumbnail;
}
