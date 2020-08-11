package com.neo.security.config;

import com.neo.security.core.validate.code.image.ImageCodeGenerate;
import com.neo.security.core.validate.code.image.ValidateCodeGenerateInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-08-11 15:48
 **/
@Configuration
public class ValidateCodeConfig {

    //如果在容器中找到imageCodeGenerate bean 则不会再执行下面的过程
    @ConditionalOnMissingBean(name = "imageCodeGenerate")
    @Bean
    public ValidateCodeGenerateInterface imageCodeGenerate() {
        return new ImageCodeGenerate();
    }
}
