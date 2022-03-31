package com.mqd.gxcj.subjectmanager.utils;

import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {


    /**
     * MD5加密方法
     * @param plainText 明文
     * @param salt  盐值
     * @return  返回加密后的字符串，32位小写字母
     */
    @SneakyThrows
    public static String MD5Lower(String plainText, String salt) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        md.update(salt.getBytes());
        return new BigInteger(1,md.digest()).toString(16);
    }
}
