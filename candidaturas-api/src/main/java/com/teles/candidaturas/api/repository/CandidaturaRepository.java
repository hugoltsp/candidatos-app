package com.teles.candidaturas.api.repository;

import com.teles.candidaturas.api.domain.entity.Candidatura;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CandidaturaRepository extends CrudRepository<Candidatura, Long> {

    List<Candidatura> findByVagaIdOrderByScoreDesc(Long vagaId);

    Optional<Candidatura> findByVagaIdAndPessoaId(Long vagaId, Long personId);

}
