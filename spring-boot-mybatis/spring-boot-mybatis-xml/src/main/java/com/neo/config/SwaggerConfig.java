package com.neo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.neo.web"))
                .paths(PathSelectors.any())
                .build();
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                // 自行修改为自己的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.neo.controller"))
//                .paths(PathSelectors.any())
//                .build().securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("客户管理")
                .description("客户管理中心 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("http://www.ityouknow.com/")
                .version("1.0")
                .contact(new Contact("纯洁的微笑", "http://www.ityouknow.com/", "ityouknow@126.com"))
                .build();
    }

//    private List<? extends SecurityScheme> securitySchemes() {
//        return Arrays.asList(new BasicAuth("Authorization"));
//    }


//    @Bean
//    public FilterRegistrationBean FilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//
//        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        // 注入过滤器
//        registration.setFilter(new RequestAuthFilter());
//        // 过滤器名称
//        registration.setName("RequestAuthFilter");
//        // 拦截规则
//        List<String> paths = Lists.newArrayList();
//        paths.add("/");
//        paths.add("/v2/*");
//        paths.add("/swagger-ui.html");
//        paths.add("/swagger-resources");
//        paths.add("/swagger-resources/configuration/ui");
//        registration.setUrlPatterns(paths);
//        // 是否自动注册 false 取消Filter的自动注册
//        registration.setEnabled(true);
//        // 过滤器顺序
//        registration.setOrder(Integer.MAX_VALUE - 2);
//
//        return registration;
//    }
}