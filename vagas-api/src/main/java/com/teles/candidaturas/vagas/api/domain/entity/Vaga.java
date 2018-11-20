package com.teles.candidaturas.vagas.api.domain.entity;

import com.teles.candidaturas.commons.constants.Localizacao;
import com.teles.candidaturas.commons.constants.Nivel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "empresa", nullable = false)
    private String empresa;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "localizacao", nullable = false)
    private Localizacao localizacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private Nivel nivel;

}