package com.zgy.learn.md5sha.utils.md5;

import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/24
 * @description: md5是摘要算法, 不是加密
 */
@Component
public class HutoolMd5Util {

    public static String md5String16(String str) {
        String md5Str = SecureUtil.md5().digestHex16(str);
        return md5Str;
    }

    public static String md5String32(String str) {
        String md5Str = SecureUtil.md5().digestHex(str);
        return md5Str;
    }

}
