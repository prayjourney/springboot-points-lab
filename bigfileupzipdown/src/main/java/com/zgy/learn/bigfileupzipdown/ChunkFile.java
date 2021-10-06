package com.zgy.learn.bigfileupzipdown;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author: pray-journey.io
 * @description: 文件分片
 * @date: created in 2021/10/6
 * @modified:
 */
public class ChunkFile {
    // 10MB=>字节
    private static final int TENMB = 10485760;
    private static final String PATH = "D:/";

    public static void main(String[] args) {
        try {
            File file = new File(PATH, "我在梦游.mp4");
            RandomAccessFile accessFile = new RandomAccessFile(file, "r");
            // 文件的大小
            long size = FileUtil.size(file);
            int chunkSize = (int) Math.ceil((double) size / TENMB);
            for (int i = 0; i < chunkSize; i++) {
                // 文件操作的指针位置
                long filePointer = accessFile.getFilePointer();
                byte[] bytes;
                if (i == chunkSize - 1) {
                    int len = (int) (size - filePointer);
                    bytes = new byte[len];
                    accessFile.read(bytes, 0, bytes.length);
                } else {
                    bytes = new byte[TENMB];
                    accessFile.read(bytes, 0, bytes.length);
                }
                FileUtil.writeBytes(bytes, new File(PATH, String.valueOf(i) + ".mp4"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
