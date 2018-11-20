package com.teles.candidaturas.pessoas.api.domain.entity;

import com.teles.candidaturas.commons.constants.Localizacao;
import com.teles.candidaturas.commons.constants.Nivel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "profissao", nullable = false)
    private String profissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "localizacao", nullable = false)
    private Localizacao localizacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private Nivel nivel;

}
