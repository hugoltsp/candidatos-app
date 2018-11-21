package com.teles.candidaturas.vagas.api.service;

import com.teles.candidaturas.vagas.api.domain.dto.VagaRequest;
import com.teles.candidaturas.vagas.api.domain.dto.VagaResponse;
import com.teles.candidaturas.vagas.api.domain.entity.Vaga;
import com.teles.candidaturas.vagas.api.repository.VagaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class VagaService {

    private final ModelMapper modelMapper;

    private final VagaRepository repository;

    public VagaService(ModelMapper modelMapper, VagaRepository repository) {

        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<VagaResponse> findById(Long id) {
        log.info("Fetching data with id: [{}].", id);

        return repository.findById(id).map(this::newVagaResponse);
    }

    @Transactional
    public void save(VagaRequest vagaRequest) {

        log.info("Saving [{}].", vagaRequest);

        repository.save(newVaga(vagaRequest));
    }

    private Vaga newVaga(VagaRequest vagaRequest) {

        return modelMapper.map(vagaRequest, Vaga.class);
    }

    private VagaResponse newVagaResponse(Vaga vaga) {

        return modelMapper.map(vaga, VagaResponse.class);
    }

}
