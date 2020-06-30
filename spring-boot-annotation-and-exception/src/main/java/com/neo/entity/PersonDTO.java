package com.neo.entity;

import com.neo.validators.PasswordEqual;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-29 17:17
 **/
@Getter
@Setter
@PasswordEqual()
public class PersonDTO {

    @Length(min = 2,max = 10,message = "长度不符")
    private String name;
    private Integer age;

    private String password1;
    private String password2;
}
