package com.hc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class AppWebConfig extends WebMvcConfigurationSupport {

    @Value("${swagger.enabled:true}")
    protected boolean swaggerEnabled;

    /**
     * swagger静态文件路径过滤
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (this.swaggerEnabled) {
            registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        super.addResourceHandlers(registry);

    }

}
