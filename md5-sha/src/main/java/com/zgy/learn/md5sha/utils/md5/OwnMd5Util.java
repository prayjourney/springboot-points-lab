package com.zgy.learn.md5sha.utils.md5;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/24
 * @description:
 */
@Component
public class OwnMd5Util {
    public static String md5String(String str) {
        try {
            // 摘要算法
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备数据
            byte[] bytes = str.getBytes();
            // 计算
            byte[] digest = md5.digest(bytes);
            // 十六进制的字符
            char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串
            for (byte b : digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b & 15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
