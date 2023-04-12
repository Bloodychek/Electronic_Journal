package com.stpc2.electronic_journal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * A class that implements the WebMvcConfigurer interface and is responsible for configuring the MVC application
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * Method responsible for processing the login page
     * @param registry - object of class ViewControllerRegistry
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    /**
     * Method for processing static resources based on location
     * @param registry - object of class ViewControllerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}