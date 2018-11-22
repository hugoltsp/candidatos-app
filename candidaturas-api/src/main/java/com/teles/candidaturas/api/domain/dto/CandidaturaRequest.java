package com.teles.candidaturas.api.domain.dto;

import com.teles.candidaturas.api.validator.annotation.PessoaExists;
import com.teles.candidaturas.api.validator.annotation.UniqueVagaAndPessoa;
import com.teles.candidaturas.api.validator.annotation.VagaExists;
import com.teles.candidaturas.api.validator.annotation.groups.ConstraintValidation;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@UniqueVagaAndPessoa(groups = ConstraintValidation.class)
public class CandidaturaRequest {

    @NotNull
    @VagaExists(groups = ConstraintValidation.class)
    private Long vagaId;

    @NotNull
    @PessoaExists(groups = ConstraintValidation.class)
    private Long pessoaId;

}
