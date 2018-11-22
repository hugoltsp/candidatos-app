package com.teles.candidaturas.api.validator;

import com.teles.candidaturas.api.client.PessoasApiClient;
import com.teles.candidaturas.api.validator.annotation.PessoaExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PessoaValidator implements ConstraintValidator<PessoaExists, Long> {

    private final PessoasApiClient pessoasApiClient;

    public PessoaValidator(PessoasApiClient pessoasApiClient) {

        this.pessoasApiClient = pessoasApiClient;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return pessoasApiClient.get(value) != null;
    }

}
