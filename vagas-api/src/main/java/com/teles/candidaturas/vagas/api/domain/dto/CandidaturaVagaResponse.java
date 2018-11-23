package com.teles.candidaturas.vagas.api.domain.dto;

import com.teles.candidaturas.commons.constants.Localizacao;
import lombok.Data;

@Data
public class CandidaturaVagaResponse {

    private String nome;

    private String profissao;

    private Localizacao localizacao;

    private Integer nivel;

    private Integer score;

}
