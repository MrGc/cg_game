package com.cg.train.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.util.SafeEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/7 11:51
 * @Version: 1.0.0
 */
public class SHA1Util {
    private static final Logger log = LoggerFactory.getLogger(SHA1Util.class);

    public static String encode(String str) {
        MessageDigest sha1;
        try {
            sha1 = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
            return "";
        }
        byte[] byteArray = SafeEncoder.encode(str);
        byte[] md5Bytes = sha1.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        log.debug("加密后：{}", hexValue);
        return hexValue.toString();

    }
}
