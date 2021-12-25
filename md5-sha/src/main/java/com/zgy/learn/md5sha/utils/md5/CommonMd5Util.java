package com.zgy.learn.md5sha.utils.md5;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/25
 * @description:
 */
@Component
public class CommonMd5Util {

    public static String md5String(String str) {
        String md5Str = DigestUtils.md5Hex(str);
        return md5Str;
    }
}
