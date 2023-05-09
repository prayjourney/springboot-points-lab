package com.zgy.learn.xmldeal.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/3
 * @description:
 */
@Service
public class EncodeService {
    /**
     * sha1加密，使用commons-codec
     *
     * @param str
     * @return
     */
    public String sha1Str(String str) {
        String sign = DigestUtils.sha1Hex(str);
        return sign;
    }

}
