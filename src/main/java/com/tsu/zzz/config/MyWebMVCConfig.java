package com.tsu.zzz.config;

import org.springframework.context.annotation.Configuration;
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
    }


}
