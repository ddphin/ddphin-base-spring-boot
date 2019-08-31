//package com.ddphin.base.swagger.configuration;
//
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * WebMvcConfig
// *
// * @Date 2019/8/30 上午10:19
// * @Author ddphin
// */
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 解决 swagger-ui.html 404报错
//        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        // 解决 doc.html 404 报错
//        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
//
//    }
//}
