package com.teles.candidaturas.pessoas.api.endpoint;

import com.teles.candidaturas.pessoas.api.domain.dto.PessoaRequest;
import com.teles.candidaturas.pessoas.api.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/pessoas")
public class PessoasEndpoint {

    private final PessoaService pessoaService;

    public PessoasEndpoint(PessoaService pessoaService) {

        this.pessoaService = pessoaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {

        return pessoaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PessoaRequest pessoaRequest) {

        pessoaService.save(pessoaRequest);
        return ResponseEntity.ok().build();
    }

}
