package com.zgy.learn.md5sha.utils.sha256;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/25
 * @description:
 */
@Component
public class CommonSha256Util {

    public static String sha256String(String str) {
        String md5Str = DigestUtils.sha256Hex(str);
        return md5Str;
    }

}
