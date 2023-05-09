package com.zgy.learn.hessianclient.config;

import com.zgy.learn.hessianclient.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * @author zgy
 * @date 2021/10/15
 */
@Configuration
public class HessianConfig {

    @Bean
    public HessianProxyFactoryBean hessianClient() {
        HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        hessianProxyFactoryBean.setServiceUrl("http://localhost:12000/hessian");
        hessianProxyFactoryBean.setServiceInterface(HelloService.class);
        return hessianProxyFactoryBean;
    }

}
