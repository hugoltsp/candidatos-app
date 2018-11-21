package com.teles.candidaturas.api.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CandidaturaRequest {

    @NotNull
    private Long vagaId;

    @NotNull
    private Long pessoaId;

}
