package com.teles.candidaturas.vagas.api.tests.service;

import com.teles.candidaturas.vagas.api.client.CandidaturasApiClient;
import com.teles.candidaturas.vagas.api.client.PessoasApiClient;
import com.teles.candidaturas.vagas.api.domain.dto.VagaResponse;
import com.teles.candidaturas.vagas.api.domain.entity.Vaga;
import com.teles.candidaturas.vagas.api.repository.VagaRepository;
import com.teles.candidaturas.vagas.api.service.VagaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PessoaServiceTest {

    @Mock
    private VagaRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PessoasApiClient pessoasApiClient;

    @Mock
    private CandidaturasApiClient candidaturasApiClient;

    private VagaService service;

    @Before
    public void setup() {
        this.service = new VagaService(modelMapper, repository, candidaturasApiClient, pessoasApiClient);
    }

    @Test
    public void findById_should_return_empty_optional_if_theres_no_data() {

        when(repository.findById(1L)).thenReturn(empty());
        verify(modelMapper, never()).map(any(), any());

        assertThat(service.findById(1L)).isEmpty();
    }

    @Test
    public void findById_should_not_return_empty_optional_if_theres_no_data() {
        Vaga vaga = new Vaga();

        when(repository.findById(1L)).thenReturn(of(vaga));
        when(modelMapper.map(vaga, VagaResponse.class)).thenReturn(new VagaResponse());

        assertThat(service.findById(1L)).isNotEmpty();
    }


}