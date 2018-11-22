package com.teles.candidaturas.api.validator.annotation;

import com.teles.candidaturas.api.validator.VagaAndPessoaCandidaturaConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {VagaAndPessoaCandidaturaConstraintValidator.class})
public @interface UniqueVagaAndPessoa {

    String message() default "This person has already applied for this position.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
