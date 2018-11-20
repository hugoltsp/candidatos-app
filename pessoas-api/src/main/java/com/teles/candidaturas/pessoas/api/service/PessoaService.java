package com.teles.candidaturas.pessoas.api.service;

import com.teles.candidaturas.pessoas.api.domain.dto.PessoaRequest;
import com.teles.candidaturas.pessoas.api.domain.dto.PessoaResponse;
import com.teles.candidaturas.pessoas.api.domain.entity.Pessoa;
import com.teles.candidaturas.pessoas.api.repository.PessoaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class PessoaService {

    private final PessoaRepository repository;

    private final ModelMapper modelMapper;

    public PessoaService(PessoaRepository repository, ModelMapper modelMapper) {

        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<PessoaResponse> findById(Long id) {
        log.info("Fetching data with id: [{}].", id);

        return repository.findById(id).map(this::newPessoaResponse);
    }

    @Transactional
    public Pessoa save(PessoaRequest pessoaRequest) {

        log.info("Saving [{}]", pessoaRequest);

        return repository.save(newPessoa(pessoaRequest));
    }

    private PessoaResponse newPessoaResponse(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaResponse.class);
    }

    private Pessoa newPessoa(PessoaRequest pessoaRequest) {
        return modelMapper.map(pessoaRequest, Pessoa.class);
    }

}
