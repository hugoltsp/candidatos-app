package com.teles.candidaturas.api.validator.annotation;

import com.teles.candidaturas.api.validator.PessoaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PessoaValidator.class})
public @interface PessoaExists {

    String message() default "There is no Pessoa for the given id.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
