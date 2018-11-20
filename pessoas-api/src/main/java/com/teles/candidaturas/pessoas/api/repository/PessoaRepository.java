package com.teles.candidaturas.pessoas.api.repository;

import com.teles.candidaturas.pessoas.api.domain.entity.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

}
