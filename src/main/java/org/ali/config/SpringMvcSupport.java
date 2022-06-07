package org.ali.config;

import org.ali.controller.interceptor.ProjectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {

    @Resource
    private ProjectInterceptor projectInterceptor;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/image/**").addResourceLocations("/image/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/plugin/**").addResourceLocations("/plugin/");
        registry.addResourceHandler("/index.html").addResourceLocations("/index.html");
        registry.addResourceHandler("/login.html").addResourceLocations("/login.html");

    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectInterceptor).addPathPatterns("/pages/**","/pages/");
        //registry.addInterceptor(projectInterceptor).addPathPatterns("/index.html","/index.html");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/books","/books/*");
        // registry.addInterceptor(projectInterceptor).addPathPatterns("/admin","/admin/*");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/emps","/emps/*");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/add_job","/add_job/*");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/off_job","/off_job/*");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/out_job","/out_job/*");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/attend_job","/attend_job/*");
    }
}
