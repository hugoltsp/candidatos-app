package com.teles.candidaturas.api.domain.dto;

import lombok.Data;

@Data
public class CandidaturaResponse {

    private Long pessoaId;

    private Long vagaId;

    private Integer score;

}
