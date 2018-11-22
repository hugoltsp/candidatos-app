package com.teles.candidaturas.api.client;

import com.teles.candidaturas.commons.constants.Localizacao;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "vagas-api")
public interface VagasApiClient {

    @GetMapping("/vagas/{id}")
    VagaResponse get(@PathVariable("id") Long id);

    @Data
    class VagaResponse {

        private Long id;

        private String empresa;

        private String titulo;

        private String descricao;

        private Localizacao localizacao;

        private Integer nivel;

    }

}
