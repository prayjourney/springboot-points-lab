package com.zgy.learn.hessianserver.component;

import com.zgy.learn.hessianserver.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;

/**
 * @author renjiaxin
 * @date 2021/10/15
 */
@Component
public class HessianServerComponent {

    @Autowired
    private HelloService helloService;

    @Bean(name = "/hessian")
    public HessianServiceExporter hessianServiceExporter() {
        // 使用Spring的HessianService做代理
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(helloService);
        hessianServiceExporter.setServiceInterface(HelloService.class);
        return hessianServiceExporter;
    }
}
