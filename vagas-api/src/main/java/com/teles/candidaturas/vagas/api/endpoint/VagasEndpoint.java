package com.teles.candidaturas.vagas.api.endpoint;

import com.teles.candidaturas.vagas.api.domain.dto.VagaRequest;
import com.teles.candidaturas.vagas.api.service.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/vagas")
public class VagasEndpoint {

    private final VagaService vagaService;

    public VagasEndpoint(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {

        return ResponseEntity.ok(vagaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VagaRequest vagaRequest) {

        return ResponseEntity.ok(linkTo(methodOn(VagasEndpoint.class)
                .get(vagaService.save(vagaRequest).getId()))
                .withSelfRel());
    }

}
