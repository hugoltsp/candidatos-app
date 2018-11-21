package com.teles.candidaturas.api.repository;

import com.teles.candidaturas.api.domain.entity.Candidatura;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidaturaRepository extends CrudRepository<Candidatura, Long> {

    List<Candidatura> findByVagaIdOrderByScoreDesc(Long vagaId);

}
