package com.teles.candidaturas.api.service;

import com.teles.candidaturas.api.client.PessoasApiClient;
import com.teles.candidaturas.api.client.PessoasApiClient.PessoaResponse;
import com.teles.candidaturas.api.client.VagasApiClient;
import com.teles.candidaturas.api.client.VagasApiClient.VagaResponse;
import com.teles.candidaturas.api.domain.dto.CandidaturaRequest;
import com.teles.candidaturas.commons.constants.Localizacao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.teles.candidaturas.commons.constants.Localizacao.*;
import static java.util.Arrays.stream;

@Service
public class ScoreCalculator {

    private static final Map<Localizacao, Integer> LOCALIZACAO_DISTANCE = mapLocalizacaoDistance();

    private final VagasApiClient vagasApiClient;

    private final PessoasApiClient pessoasApiClient;

    public ScoreCalculator(VagasApiClient vagasApiClient, PessoasApiClient pessoasApiClient) {

        this.vagasApiClient = vagasApiClient;
        this.pessoasApiClient = pessoasApiClient;
    }

    public int calculateScore(CandidaturaRequest candidatura) {

        PessoaResponse pessoaResponse = pessoasApiClient.get(candidatura.getPessoaId());

        VagaResponse vagaResponse = vagasApiClient.get(candidatura.getVagaId());

        return (getLevelScore(vagaResponse, pessoaResponse) + getDistanceScore(vagaResponse, pessoaResponse)) / 2;
    }

    private int getDistanceScore(VagaResponse vagaResponse,
                                 PessoaResponse pessoaResponse) {
        int score = 0;

        int distance = sumDistanceBetweenPoints(vagaResponse.getLocalizacao(), pessoaResponse.getLocalizacao());

        if (distance >= 0 && distance <= 5) {
            score = 100;
        } else if (distance > 5 && distance <= 10) {
            score = 75;
        } else if (distance > 10 && distance <= 15) {
            score = 50;
        } else if (distance > 15 && distance <= 20) {
            score = 25;
        }

        return score;
    }

    private int getLevelScore(VagaResponse vagaResponse, PessoaResponse pessoaResponse) {
        return 100 - 25 * ((vagaResponse.getNivel() - pessoaResponse.getNivel()));
    }

    private int sumDistanceBetweenPoints(Localizacao positionX, Localizacao positionY) {

        return stream(values()).filter(thisLocalizacao -> (thisLocalizacao.ordinal() >= positionX.ordinal() && thisLocalizacao.ordinal() <= positionY.ordinal()))
                .mapToInt(LOCALIZACAO_DISTANCE::get).sum();
    }

    private static final Map<Localizacao, Integer> mapLocalizacaoDistance() {

        Map<Localizacao, Integer> map = new HashMap<>();

        map.put(A, 0);
        map.put(B, 5);
        map.put(C, 7);
        map.put(D, 3);
        map.put(E, 10);
        map.put(F, 8);

        return map;
    }

}
