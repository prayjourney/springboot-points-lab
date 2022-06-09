package com.zgy.learn.callthenback.useinterface;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/5/24
 * @modified:
 */
public interface ShoppingCallback {
    void success(String name, String price);

    void fail(String name);

}
