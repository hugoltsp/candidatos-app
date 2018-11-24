package com.teles.candidaturas.vagas.api.tests.service;

import com.teles.candidaturas.api.domain.dto.CandidaturaResponse;
import com.teles.candidaturas.api.domain.entity.Candidatura;
import com.teles.candidaturas.api.repository.CandidaturaRepository;
import com.teles.candidaturas.api.service.CandidaturaService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CandidaturaServiceTest {

    @Mock
    private CandidaturaRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private CandidaturaService service;

    @Before
    public void setup() {
        this.service = new CandidaturaService(repository, modelMapper);
    }

    @Test
    public void findByVagaId_should_return_empty_optional_if_theres_no_data() {

        when(repository.findByVagaIdOrderByScoreDesc(1L)).thenReturn(emptyList());
        verify(modelMapper, never()).map(any(), any());

        assertThat(service.findByVagaId(1L)).isEmpty();
    }

    @Test
    public void findById_should_not_return_empty_optional_if_theres_no_data() {
        Candidatura candidatura = new Candidatura();

        when(repository.findByVagaIdOrderByScoreDesc(1L)).thenReturn(singletonList(candidatura));
        when(modelMapper.map(candidatura, CandidaturaResponse.class)).thenReturn(new CandidaturaResponse());

        assertThat(service.findByVagaId(1L)).isNotEmpty();
    }


}