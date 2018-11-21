package com.teles.candidaturas.api.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "candidatura", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pessoa_id", "vaga_id"})
})
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pessoa_id", nullable = false)
    private Long pessoaId;

    @Column(name = "vaga_id", nullable = false)
    private Long vagaId;

    @Column(name = "score", nullable = false)
    private Integer score;

}
