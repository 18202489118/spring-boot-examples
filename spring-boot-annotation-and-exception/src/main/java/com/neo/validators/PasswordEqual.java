package com.neo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-29 17:16
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordEqual {

    int min() default 4;

    int max() default 6;

    String message() default "password is wrong!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
