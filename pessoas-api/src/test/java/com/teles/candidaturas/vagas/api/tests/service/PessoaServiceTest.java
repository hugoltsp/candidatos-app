package com.teles.candidaturas.pessoas.api.tests.service;

import com.teles.candidaturas.pessoas.api.domain.dto.PessoaResponse;
import com.teles.candidaturas.pessoas.api.domain.entity.Pessoa;
import com.teles.candidaturas.pessoas.api.repository.PessoaRepository;
import com.teles.candidaturas.pessoas.api.service.PessoaService;
import org.assertj.core.api.Assertions;
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
    private PessoaRepository pessoaRepository;

    @Mock
    private ModelMapper modelMapper;

    private PessoaService pessoaService;

    @Before
    public void setup() {
        this.pessoaService = new PessoaService(pessoaRepository, modelMapper);
    }

    @Test
    public void findById_should_return_empty_optional_if_theres_no_data() {

        when(pessoaRepository.findById(1L)).thenReturn(empty());
        verify(modelMapper, never()).map(any(), any());

        assertThat(pessoaService.findById(1L)).isEmpty();
    }

    @Test
    public void findById_should_not_return_empty_optional_if_theres_no_data() {

        Pessoa pessoa = new Pessoa();

        when(pessoaRepository.findById(1L)).thenReturn(of(pessoa));
        when(modelMapper.map(pessoa, PessoaResponse.class)).thenReturn(new PessoaResponse());

        assertThat(pessoaService.findById(1L)).isNotEmpty();
    }


}