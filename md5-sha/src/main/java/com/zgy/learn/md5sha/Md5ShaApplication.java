package com.zgy.learn.md5sha;

import com.zgy.learn.md5sha.utils.md5.HutoolMd5Util;
import com.zgy.learn.md5sha.utils.md5.OwnMd5Util;
import com.zgy.learn.md5sha.utils.md5.SpringMd5Util;
import com.zgy.learn.md5sha.utils.sha256.HutoolSha256Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://blog.csdn.net/le_17_4_6/article/details/103022176
 */
@SpringBootApplication
public class Md5ShaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Md5ShaApplication.class, args);
        System.out.println(HutoolMd5Util.md5String16("hello"));
        System.out.println(HutoolMd5Util.md5String32("hello"));
        System.out.println(SpringMd5Util.md5String("hello"));
        System.out.println(OwnMd5Util.md5String("hello"));
        System.out.println(HutoolSha256Util.sha256String("hello"));

    }

}
