package com.zgy.learn.hessianserver.config;

import com.zgy.learn.hessianserver.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * @author zgy
 * @date 2021/10/15
 */
@Configuration
public class HessianServerConfig {

    @Autowired
    private HelloService helloService;

    /**
     * 1. HessianServiceExporter是由Spring.web框架提供的Hessian工具类,能够将bean转化为Hessian服务
     * 2. @Bean(name = "/hessian")加斜杠方式会被spring暴露服务路径, 发布服务。
     */
    @Bean(name = "/hessian")
    public HessianServiceExporter hessianServiceExporter() {
        // 使用Spring的HessianService做代理
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(helloService);
        hessianServiceExporter.setServiceInterface(HelloService.class);
        return hessianServiceExporter;
    }

}
