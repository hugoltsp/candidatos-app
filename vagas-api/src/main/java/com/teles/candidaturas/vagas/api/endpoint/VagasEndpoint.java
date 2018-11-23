package com.teles.candidaturas.vagas.api.endpoint;

import com.teles.candidaturas.vagas.api.domain.dto.CandidaturaVagaResponse;
import com.teles.candidaturas.vagas.api.domain.dto.VagaRequest;
import com.teles.candidaturas.vagas.api.service.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagasEndpoint {

    private final VagaService vagaService;

    public VagasEndpoint(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {

        return vagaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}/candidaturas/ranking")
    public ResponseEntity<?> getRanking(@PathVariable Long id) {

        ResponseEntity<?> responseEntity;

        List<CandidaturaVagaResponse> rankedCandidaturas = vagaService.findVagasRankedByCandidatura(id);

        if (rankedCandidaturas.isEmpty()) {
            responseEntity = ResponseEntity.noContent().build();
        } else {
            responseEntity = ResponseEntity.ok(rankedCandidaturas);
        }

        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VagaRequest vagaRequest) {

        vagaService.save(vagaRequest);
        return ResponseEntity.ok().build();
    }

}
