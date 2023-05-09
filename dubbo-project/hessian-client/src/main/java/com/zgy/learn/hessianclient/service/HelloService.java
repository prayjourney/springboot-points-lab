package com.zgy.learn.hessianclient.service;

/**
 * @author zgy
 * @date 2021/10/15
 * @note 面向接口编程, 把server之中的接口, 搬过来， 然后在调用处注入即可
 */
public interface HelloService {
    public String sayHello();

    public String sayHelloWithName(String name);

}
