package com.teles.candidaturas.commons.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum Nivel {

    ESTAGIARIO(1),
    JUNIOR(2),
    PLENO(3),
    SENIOR(4),
    ESPECIALISTA(5);

    private static final Map<Integer, Nivel> ORDINAL_MAPPING =
            Arrays.stream(Nivel.values()).collect(Collectors.toMap(Nivel::getNivelExperiencia, Function.identity()));

    private final int nivelExperiencia;

    Nivel(int nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }

    public static Nivel getNivel(Integer nivelExperiencia) {

        return ORDINAL_MAPPING.get(nivelExperiencia);
    }

}
