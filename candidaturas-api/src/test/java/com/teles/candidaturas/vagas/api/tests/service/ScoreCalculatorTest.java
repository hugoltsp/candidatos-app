package com.teles.candidaturas.vagas.api.tests.service;

import com.teles.candidaturas.api.client.PessoasApiClient;
import com.teles.candidaturas.api.client.PessoasApiClient.PessoaResponse;
import com.teles.candidaturas.api.client.VagasApiClient;
import com.teles.candidaturas.api.client.VagasApiClient.VagaResponse;
import com.teles.candidaturas.api.domain.dto.CandidaturaRequest;
import com.teles.candidaturas.api.service.ScoreCalculator;
import com.teles.candidaturas.commons.constants.Localizacao;
import com.teles.candidaturas.commons.constants.Nivel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ScoreCalculatorTest {

    @Mock
    private VagasApiClient vagasApiClient;

    @Mock
    private PessoasApiClient pessoasApiClient;

    private ScoreCalculator scoreCalculator;

    @Before
    public void setup() {
        this.scoreCalculator = new ScoreCalculator(vagasApiClient, pessoasApiClient);
    }

    @Test
    public void assert_100_score_on_job_application() {

        CandidaturaRequest candidaturaRequest = new CandidaturaRequest();
        candidaturaRequest.setVagaId(1L);
        candidaturaRequest.setPessoaId(1L);

        VagaResponse vagaResponse = new VagaResponse();
        vagaResponse.setNivel(Nivel.ESPECIALISTA.getNivelExperiencia());
        vagaResponse.setLocalizacao(Localizacao.A);

        PessoaResponse pessoaResponse = new PessoaResponse();
        pessoaResponse.setNivel(Nivel.ESPECIALISTA.getNivelExperiencia());
        pessoaResponse.setLocalizacao(Localizacao.A);

        when(vagasApiClient.get(1L)).thenReturn(vagaResponse);
        when(pessoasApiClient.get(1L)).thenReturn(pessoaResponse);

        assertThat(scoreCalculator.calculateScore(candidaturaRequest)).isEqualTo(100);
    }

    @Test
    public void assert_50_score_on_job_application() {

        CandidaturaRequest candidaturaRequest = new CandidaturaRequest();
        candidaturaRequest.setVagaId(1L);
        candidaturaRequest.setPessoaId(1L);

        VagaResponse vagaResponse = new VagaResponse();
        vagaResponse.setNivel(Nivel.ESPECIALISTA.getNivelExperiencia());
        vagaResponse.setLocalizacao(Localizacao.A);

        PessoaResponse pessoaResponse = new PessoaResponse();
        pessoaResponse.setNivel(Nivel.ESPECIALISTA.getNivelExperiencia());
        pessoaResponse.setLocalizacao(Localizacao.F);

        when(vagasApiClient.get(1L)).thenReturn(vagaResponse);
        when(pessoasApiClient.get(1L)).thenReturn(pessoaResponse);

        assertThat(scoreCalculator.calculateScore(candidaturaRequest)).isEqualTo(50);
    }

    @Test
    public void assert_0_score_on_job_application() {

        CandidaturaRequest candidaturaRequest = new CandidaturaRequest();
        candidaturaRequest.setVagaId(1L);
        candidaturaRequest.setPessoaId(1L);

        VagaResponse vagaResponse = new VagaResponse();
        vagaResponse.setNivel(Nivel.ESPECIALISTA.getNivelExperiencia());
        vagaResponse.setLocalizacao(Localizacao.A);

        PessoaResponse pessoaResponse = new PessoaResponse();
        pessoaResponse.setNivel(Nivel.ESTAGIARIO.getNivelExperiencia());
        pessoaResponse.setLocalizacao(Localizacao.F);

        when(vagasApiClient.get(1L)).thenReturn(vagaResponse);
        when(pessoasApiClient.get(1L)).thenReturn(pessoaResponse);

        assertThat(scoreCalculator.calculateScore(candidaturaRequest)).isEqualTo(0);
    }


}