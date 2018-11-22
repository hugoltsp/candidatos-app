package com.teles.candidaturas.api.validator;

import com.teles.candidaturas.api.domain.dto.CandidaturaRequest;
import com.teles.candidaturas.api.service.CandidaturaService;
import com.teles.candidaturas.api.validator.annotation.UniqueVagaAndPessoa;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VagaAndPessoaCandidaturaConstraintValidator implements ConstraintValidator<UniqueVagaAndPessoa, CandidaturaRequest> {

    private final CandidaturaService candidaturaService;

    public VagaAndPessoaCandidaturaConstraintValidator(CandidaturaService candidaturaService) {

        this.candidaturaService = candidaturaService;
    }

    @Override
    public boolean isValid(CandidaturaRequest candidaturaRequest,
                           ConstraintValidatorContext constraintValidatorContext) {

        return !candidaturaService.findByVagaIdAndPessoaId(candidaturaRequest.getVagaId(),
                candidaturaRequest.getPessoaId()).isPresent();
    }


}
