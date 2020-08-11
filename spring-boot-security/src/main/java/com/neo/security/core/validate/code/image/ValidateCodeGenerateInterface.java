package com.neo.security.core.validate.code.image;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-08-11 15:40
 **/
public interface ValidateCodeGenerateInterface {

    ImageCode generate(ServletWebRequest webRequest);
}
