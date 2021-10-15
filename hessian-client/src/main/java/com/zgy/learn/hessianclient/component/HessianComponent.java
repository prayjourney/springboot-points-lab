package com.zgy.learn.hessianclient.component;

import com.zgy.learn.hessianclient.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author zgy
 * @date 2021/10/15
 */
@Component
public class HessianComponent {
    @Bean
    public HessianProxyFactoryBean hessianClient() {
        HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        hessianProxyFactoryBean.setServiceUrl("http://localhost:12000/hessian");
        hessianProxyFactoryBean.setServiceInterface(HelloService.class);
        return hessianProxyFactoryBean;
    }

}
