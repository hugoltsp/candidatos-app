package com.teles.candidaturas.api.endpoint;

import com.teles.candidaturas.api.domain.dto.CandidaturaRequest;
import com.teles.candidaturas.api.domain.dto.CandidaturaResponse;
import com.teles.candidaturas.api.service.CandidaturaService;
import com.teles.candidaturas.api.validator.annotation.groups.OrderedValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturasEndpoint {

    private final CandidaturaService candidaturaService;

    public CandidaturasEndpoint(CandidaturaService candidaturaService) {

        this.candidaturaService = candidaturaService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated(value = OrderedValidation.class)
                                        @RequestBody CandidaturaRequest candidaturaRequest) {

        candidaturaService.save(candidaturaRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/vaga/{vagaId}")
    public ResponseEntity<?> findByVagaId(@PathVariable Long vagaId) {

        ResponseEntity<?> responseEntity;

        List<CandidaturaResponse> candidaturaList = candidaturaService.findByVagaId(vagaId);

        if (candidaturaList.isEmpty()) {
            responseEntity = ResponseEntity.noContent().build();
        } else {
            responseEntity = ResponseEntity.ok(candidaturaList);
        }

        return responseEntity;
    }

}
