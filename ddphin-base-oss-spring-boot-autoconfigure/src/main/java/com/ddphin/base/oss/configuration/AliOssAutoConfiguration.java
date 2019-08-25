package com.ddphin.base.oss.configuration;

import com.ddphin.base.oss.controller.AliOssController;
import com.ddphin.base.oss.service.AliOssService;
import com.ddphin.base.oss.service.impl.AliOssServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: AliOssConfiguration
 * Function:  AliOssConfiguration
 * Date:      2019/6/21 下午2:30
 * Author     ddphin
 * Version    V1.0
 */
@Configuration
public class AliOssAutoConfiguration {
    @Bean
    @ConfigurationProperties(prefix="ali.oss")
    public AliOSSProperties ossproperties() {
        return new AliOSSProperties();
    }

    @Bean
    public AliOssService aliOssService() {
        return new AliOssServiceImpl(this.ossproperties());
    }

    @Bean
    public AliOssController aliOssController() {
        return new AliOssController(this.aliOssService());
    }
}
