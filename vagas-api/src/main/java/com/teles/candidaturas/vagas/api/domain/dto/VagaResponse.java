package com.teles.candidaturas.vagas.api.domain.dto;

import com.teles.candidaturas.commons.constants.Localizacao;
import lombok.Data;

@Data
public class VagaResponse {

    private Long id;

    private String empresa;

    private String titulo;

    private String descricao;

    private Localizacao localizacao;

    private Integer nivel;


}
