package com.teles.candidaturas.vagas.api.client;

import com.teles.candidaturas.commons.constants.Localizacao;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "pessoas-api")
public interface PessoasApiClient {

    @GetMapping("/pessoas/{id}")
    PessoaResponse get(@PathVariable("id") Long id);

    @Data
    class PessoaResponse {

        private Long id;

        private String nome;

        private String profissao;

        private Localizacao localizacao;

        private Integer nivel;

    }
}
