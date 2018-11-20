package com.teles.candidaturas.vagas.api.domain.dto;

import com.teles.candidaturas.commons.constants.Localizacao;
import com.teles.candidaturas.commons.constants.Nivel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VagaRequest {

    @NotBlank
    private String empresa;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private Localizacao localizacao;

    @NotNull
    private Nivel nivel;

    public void setNivel(Integer nivel) {

        this.nivel = Nivel.getNivel(nivel);
    }

}
