package com.neo.validators;

import com.neo.entity.PersonDTO;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-29 17:31
 **/
public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersonDTO> {
    private int min;
    private int max;

    @Override
    public boolean isValid(PersonDTO personDTO, ConstraintValidatorContext context) {
        //此处可以判断密码的长度 todo

        String p1 = personDTO.getPassword1();
        String p2 = personDTO.getPassword2();
        return p1.equals(p2);
    }

    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }
}
