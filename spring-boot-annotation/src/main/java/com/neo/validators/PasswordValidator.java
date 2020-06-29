package com.neo.validators;

import com.neo.entity.PersonDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-29 17:31
 **/
public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersonDTO> {
    @Override
    public boolean isValid(PersonDTO personDTO, ConstraintValidatorContext context) {
        String p1 = personDTO.getPassword1();
        String p2 = personDTO.getPassword2();
        return p1.equals(p2);
    }

    @Override
    public void initialize(PasswordEqual constraintAnnotation) {

    }
}
