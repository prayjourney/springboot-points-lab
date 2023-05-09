package com.zgy.learn.verificationtianai.handler;

import cloud.tianai.captcha.slider.SliderCaptchaApplication;
import cloud.tianai.captcha.template.slider.resource.ResourceStore;
import cloud.tianai.captcha.template.slider.resource.SliderCaptchaResourceManager;
import cloud.tianai.captcha.template.slider.resource.common.model.dto.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zgy
 * @date 2022/4/22
 */
@Component
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private SliderCaptchaApplication sliderCaptchaApplication;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        SliderCaptchaResourceManager sliderCaptchaResourceManager = sliderCaptchaApplication.getSliderCaptchaResourceManager();
        ResourceStore resourceStore = sliderCaptchaResourceManager.getResourceStore();
        // 清除内置的背景图片
        resourceStore.clearResources();

        // 添加自定义背景图片
        resourceStore.addResource(new Resource("classpath", "static/capimgs/a.jpg"));
        resourceStore.addResource(new Resource("classpath", "static/capimgs/b.jpg"));
        resourceStore.addResource(new Resource("classpath", "static/capimgs/c.jpg"));
        resourceStore.addResource(new Resource("classpath", "static/capimgs/d.jpg"));
        resourceStore.addResource(new Resource("classpath", "static/capimgs/e.jpg"));
        resourceStore.addResource(new Resource("classpath", "static/capimgs/g.jpg"));
        resourceStore.addResource(new Resource("classpath", "static/capimgs/h.jpg"));
        resourceStore.addResource(new Resource("classpath", "static/capimgs/i.jpg"));
        resourceStore.addResource(new Resource("classpath", "static/capimgs/j.jpg"));

    }

}
