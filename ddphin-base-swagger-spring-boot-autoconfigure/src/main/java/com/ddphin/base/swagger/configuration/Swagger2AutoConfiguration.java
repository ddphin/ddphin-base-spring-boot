package com.ddphin.base.swagger.configuration;

import com.ddphin.base.swagger.plugin.ExpandedEnumParamPlugin;
import com.ddphin.base.swagger.plugin.GenericEnumParamPlugin;
import com.ddphin.base.swagger.plugin.ModelEnumPropertyPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2Config
 *
 * @Date 2019/8/30 上午10:04
 * @Author ddphin
 */
@Configuration
@EnableSwagger2
public class Swagger2AutoConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Api文档")
                .description("DDphin Swagger")
                //服务条款网址
                .termsOfServiceUrl("https://github.com/ddphin")
                .version("1.0")
                .build();
    }

    @Bean
    public ExpandedEnumParamPlugin expandedEnumParamPlugin() {
        return new ExpandedEnumParamPlugin();
    }
    @Bean
    public GenericEnumParamPlugin genericEnumParamPlugin() {
        return new GenericEnumParamPlugin();
    }

    @Bean
    public ModelEnumPropertyPlugin modelEnumPropertyPlugin() {
        return new ModelEnumPropertyPlugin();
    }
}
