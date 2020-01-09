package com.sunlands.addTinyPic.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="sfs")
public class SFSConf {
    private String accessKey;
    private String secretKey;
    private String domain;
    private String tokenURL;
    private String downloadURL;
    private String uploadURL;
}
