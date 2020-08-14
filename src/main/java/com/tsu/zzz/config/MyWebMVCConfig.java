package com.tsu.zzz.config;

import com.tsu.zzz.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/blog").setViewName("blog");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/tags").setViewName("tags");
        registry.addViewController("/types").setViewName("types");
        registry.addViewController("/archives").setViewName("archives");
        registry.addViewController("/admin").setViewName("admin/login");
        registry.addViewController("/types").setViewName("admin/types");
        registry.addViewController("/admin/index").setViewName("admin/index");
        registry.addViewController("/admin/type-input").setViewName("admin/type-input");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin");
    }
}
