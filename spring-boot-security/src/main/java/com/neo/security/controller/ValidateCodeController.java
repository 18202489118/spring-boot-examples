package com.neo.security.controller;

import com.neo.security.core.validate.code.image.ImageCode;
import com.neo.security.core.validate.code.image.ValidateCodeGenerateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 验证码
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-07-31 14:45
 **/
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private ValidateCodeGenerateInterface imageCodeGenerate;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ImageCode imageCode = imageCodeGenerate.generate(new ServletWebRequest(req));
        sessionStrategy.setAttribute(new ServletWebRequest(req), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", res.getOutputStream());
    }
}
