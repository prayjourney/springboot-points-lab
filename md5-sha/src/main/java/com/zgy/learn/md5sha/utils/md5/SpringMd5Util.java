package com.zgy.learn.md5sha.utils.md5;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/24
 * @description:
 */
@Component
public class SpringMd5Util {

    public static String md5String(String str) {
        String md5Str = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5Str;
    }

}
