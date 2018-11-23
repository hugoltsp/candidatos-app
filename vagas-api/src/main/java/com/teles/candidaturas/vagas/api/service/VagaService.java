package com.teles.candidaturas.vagas.api.service;

import com.teles.candidaturas.vagas.api.client.CandidaturasApiClient;
import com.teles.candidaturas.vagas.api.client.PessoasApiClient;
import com.teles.candidaturas.vagas.api.domain.dto.CandidaturaVagaResponse;
import com.teles.candidaturas.vagas.api.domain.dto.VagaRequest;
import com.teles.candidaturas.vagas.api.domain.dto.VagaResponse;
import com.teles.candidaturas.vagas.api.domain.entity.Vaga;
import com.teles.candidaturas.vagas.api.repository.VagaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VagaService {

    private final ModelMapper modelMapper;

    private final VagaRepository repository;

    private final CandidaturasApiClient candidaturasApiClient;

    private final PessoasApiClient pessoasApiClient;

    public VagaService(ModelMapper modelMapper, VagaRepository repository,
                       CandidaturasApiClient candidaturasApiClient, PessoasApiClient pessoasApiClient) {

        this.modelMapper = modelMapper;
        this.repository = repository;
        this.candidaturasApiClient = candidaturasApiClient;
        this.pessoasApiClient = pessoasApiClient;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<VagaResponse> findById(Long id) {
        log.info("Fetching data with id: [{}].", id);

        return repository.findById(id).map(this::newVagaResponse);
    }

    public List<CandidaturaVagaResponse> findVagasRankedByCandidatura(Long vagaId) {

        List<CandidaturaVagaResponse> responses = new ArrayList<>();

        List<CandidaturasApiClient.CandidaturaResponse> candidaturaResponses = getCandidaturasByVagaId(vagaId);

        if (!CollectionUtils.isEmpty(candidaturaResponses)) {
            candidaturaResponses.stream()
                    .map(this::mapToCandidaturaVagaResponse)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(responses::add);
        }

        return responses;
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


    private Optional<CandidaturaVagaResponse> mapToCandidaturaVagaResponse(CandidaturasApiClient.CandidaturaResponse candidatura) {

        return getPessoaById(candidatura.getPessoaId()).map(pessoaResponse -> {

            CandidaturaVagaResponse candidaturaVagaResponse = new CandidaturaVagaResponse();

            candidaturaVagaResponse.setLocalizacao(pessoaResponse.getLocalizacao());
            candidaturaVagaResponse.setNivel(pessoaResponse.getNivel());
            candidaturaVagaResponse.setNome(pessoaResponse.getNome());
            candidaturaVagaResponse.setProfissao(pessoaResponse.getProfissao());
            candidaturaVagaResponse.setScore(candidatura.getScore());

            return candidaturaVagaResponse;

        });

    }

    private Optional<PessoasApiClient.PessoaResponse> getPessoaById(Long pessoaId) {
        log.info("Fetching Pessoa by id [{}]", pessoaId);

        return Optional.ofNullable(pessoasApiClient.get(pessoaId));
    }

    private List<CandidaturasApiClient.CandidaturaResponse> getCandidaturasByVagaId(Long vagaId) {
        log.info("Fetching Candidaturas by id [{}]", vagaId);

        return candidaturasApiClient.findByVagaId(vagaId);
    }
}
