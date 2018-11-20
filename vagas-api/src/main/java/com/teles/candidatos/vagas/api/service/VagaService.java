package com.teles.candidatos.vagas.api.service;

import com.teles.candidatos.vagas.api.domain.dto.VagaRequest;
import com.teles.candidatos.vagas.api.domain.dto.VagaResponse;
import com.teles.candidatos.vagas.api.domain.entities.Vaga;
import com.teles.candidatos.vagas.api.exception.VagaNotFoundException;
import com.teles.candidatos.vagas.api.repository.VagaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class VagaService {

    private final ModelMapper modelMapper;

    private final VagaRepository vagaRepository;

    public VagaService(ModelMapper modelMapper, VagaRepository vagaRepository) {

        this.modelMapper = modelMapper;
        this.vagaRepository = vagaRepository;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public VagaResponse findById(Long id) {
        log.info("Fetching data with id: [{}].", id);

        return vagaRepository.findById(id)
                .map(this::newVagaResponse)
                .orElseThrow(() -> new VagaNotFoundException(String.format("Could not find an entity for the given id: [%s]", id)));
    }

    @Transactional
    public Vaga save(VagaRequest vagaRequest) {

        log.info("Saving [{}]", vagaRequest);

        return vagaRepository.save(newVagaEntity(vagaRequest));
    }

    private Vaga newVagaEntity(VagaRequest vagaRequest) {

        return modelMapper.map(vagaRequest, Vaga.class);
    }

    private VagaResponse newVagaResponse(Vaga vaga) {

        return modelMapper.map(vaga, VagaResponse.class);
    }

}
