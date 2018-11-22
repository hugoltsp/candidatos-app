package com.teles.candidaturas.api.validator;

import com.teles.candidaturas.api.client.VagasApiClient;
import com.teles.candidaturas.api.validator.annotation.VagaExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VagaValidator implements ConstraintValidator<VagaExists, Long> {

    private final VagasApiClient vagasApiClient;

    public VagaValidator(VagasApiClient vagasApiClient) {

        this.vagasApiClient = vagasApiClient;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return vagasApiClient.get(value) != null;
    }

}
