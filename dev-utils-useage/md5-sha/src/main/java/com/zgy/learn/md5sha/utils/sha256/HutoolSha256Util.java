package com.zgy.learn.md5sha.utils.sha256;

import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/24
 * @description: md5是摘要算法, 不是加密
 */
@Component
public class HutoolSha256Util {

    public static String sha256String(String str) {
        String sha256Str = SecureUtil.sha256(str);
        return sha256Str;
    }

}
