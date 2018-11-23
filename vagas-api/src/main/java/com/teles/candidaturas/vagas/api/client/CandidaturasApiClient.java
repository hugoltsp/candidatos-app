package com.teles.candidaturas.vagas.api.client;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "candidaturas-api")
public interface CandidaturasApiClient {

    @GetMapping("/candidaturas/vaga/{vagaId}")
    List<CandidaturaResponse> findByVagaId(@PathVariable("vagaId") Long vagaId);

    @Data
    class CandidaturaResponse {

        private Long pessoaId;

        private Long vagaId;

        private Integer score;

    }

}
