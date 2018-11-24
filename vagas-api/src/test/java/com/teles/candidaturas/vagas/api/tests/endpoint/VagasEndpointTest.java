package com.teles.candidaturas.vagas.api.tests.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teles.candidaturas.commons.constants.Localizacao;
import com.teles.candidaturas.vagas.api.domain.dto.VagaResponse;
import com.teles.candidaturas.vagas.api.endpoint.VagasEndpoint;
import com.teles.candidaturas.vagas.api.service.VagaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
public class VagasEndpointTest {

    private static final String URL = "/vagas";

    @Mock
    private VagaService service;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new VagasEndpoint(service)).build();
    }

    @Test
    public void should_expect_204_when_there_is_no_data_to_retrieve() throws Exception {
        when(service.findById(1L)).thenReturn(empty());

        mockMvc.perform(get(URL + "/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_expect_200_when_there_is_data_for_given_id() throws Exception {
        when(service.findById(1L)).thenReturn(of(new VagaResponse()));

        mockMvc.perform(get(URL + "/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void should_expect_200_when_request_is_valid() throws Exception {

        mockMvc.perform(post(URL)
                .content(new Request("empresaX",
                        "Java Developer",
                        "description",
                        Localizacao.A,
                        3).toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void should_expect_400_when_descricao_is_blank() throws Exception {

        mockMvc.perform(post(URL)
                .content(new Request("empresaX",
                        "Java Developer",
                        null,
                        Localizacao.A,
                        3).toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_expect_400_when_empresa_is_blank() throws Exception {

        mockMvc.perform(post(URL)
                .content(new Request(null,
                        "XPTO",
                        "description",
                        Localizacao.A,
                        3).toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void should_expect_400_when_titulo_is_blank() throws Exception {

        mockMvc.perform(post(URL)
                .content(new Request("empresaX",
                        "",
                        "description",
                        Localizacao.A,
                        3).toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_expect_400_when_localizacao_is_null() throws Exception {

        mockMvc.perform(post(URL)
                .content(new Request("empresaX",
                        "Java Developer",
                        "description",
                        null,
                        3).toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_expect_400_when_nivel_is_null() throws Exception {

        mockMvc.perform(post(URL)
                .content(new Request("empresaX",
                        "Java Developer",
                        "description",
                        Localizacao.A,
                        null).toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    private class Request {

        private final String empresa;
        private final String titulo;
        private final String descricao;
        private final Localizacao localizacao;
        private final Integer nivel;

        public Request(String empresa, String titulo, String descricao, Localizacao localizacao, Integer nivel) {
            this.empresa = empresa;
            this.titulo = titulo;
            this.descricao = descricao;
            this.localizacao = localizacao;
            this.nivel = nivel;
        }


        public String getEmpresa() {
            return empresa;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getDescricao() {
            return descricao;
        }

        public Localizacao getLocalizacao() {
            return localizacao;
        }

        public Integer getNivel() {
            return nivel;
        }

        public String toJson() throws JsonProcessingException {
            return new ObjectMapper().writeValueAsString(this);
        }
    }

}
