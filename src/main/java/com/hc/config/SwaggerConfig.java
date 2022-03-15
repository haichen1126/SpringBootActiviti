package com.hc.config;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@Profile({"dev", "test"})
public class SwaggerConfig {

    @Value("${swagger.enabled:false}")
    private Boolean enabled;

    private static final List<Parameter> PARAMETER_LIST = new ArrayList<>(Arrays.asList( new ParameterBuilder().name("token").description("token令牌").modelRef(new ModelRef("String"))
            .parameterType("header").defaultValue("abc")
            .required(false).build()));

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo相关")
                .apiInfo(new ApiInfoBuilder().title("demo相关API").description("demo相关API文档").build())
                .pathMapping("/")
                .enable(enabled).select()
                .apis(RequestHandlerSelectors.basePackage("com.hc.controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo())
//                .globalOperationParameters(PARAMETER_LIST)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统相关")
                .apiInfo(new ApiInfoBuilder().title("系统相关API").description("系统相关API文档").build())
                .pathMapping("/")
                .enable(enabled).select()
                .apis(RequestHandlerSelectors.basePackage("com.hc.sys"))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo())
//                .globalOperationParameters(PARAMETER_LIST)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("HC", "192.168.1.13", "1581854528@qq.com");
        return new ApiInfoBuilder()
                .title("汐的demo")
                .description("汐的demo")
                .termsOfServiceUrl("192.168.1.13")
                .version("1.0.0")
                .contact(contact)
                .description("HC的API接口文档")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!login).*$"))
                        .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

}
