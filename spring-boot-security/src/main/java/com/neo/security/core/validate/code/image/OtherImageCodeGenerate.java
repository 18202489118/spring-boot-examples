package com.neo.security.core.validate.code.image;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: <p>这里可以自定义其他更好用的图形验证码，
 * <p>启动时如果容器里没有imageCodeGenerate，
 * <p>将会用ValidateCodeConfig中配置的imageCodeGenerate
 *
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-08-11 15:41
 **/
@Component("imageCodeGenerate11111")
public class OtherImageCodeGenerate implements ValidateCodeGenerateInterface {


    @Override
    public ImageCode generate(ServletWebRequest req) {
        System.out.println("这里是可自定义的更高级的验证码");
        return null;
    }
}
