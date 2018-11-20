package com.teles.candidaturas.pessoas.api.domain.dto;

import com.teles.candidaturas.commons.constants.Localizacao;
import lombok.Data;

@Data
public class PessoaResponse {

    private Long id;

    private String nome;

    private String profissao;

    private Localizacao localizacao;

    private Integer nivel;

}
