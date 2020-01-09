package com.sunlands.addTinyPic.common;

public class SFSKeyGenerator {
    public static String IMG_DIR = "img";

    public static String genJpgKey(String key) {
        int idx = key.lastIndexOf('.');

        if (idx < 1) {
            return null;
        }

        return key.substring(0, idx) + ".jpg";
    }

    public static String genTinyKey(String key) {
        int idx = key.lastIndexOf('.');

        if (idx < 1) {
            return null;
        }

        return key.substring(0, idx) + "_640_480.png";
    }

    public static String getFileNameFromKey(String key) {
        String[] split = key.split("/");

        if(split == null || split.length == 0) {
            return null;
        }

        return split[split.length - 1];
    }
}
