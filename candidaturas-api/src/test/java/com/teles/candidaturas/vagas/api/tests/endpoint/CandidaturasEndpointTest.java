package com.teles.candidaturas.vagas.api.tests.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teles.candidaturas.api.domain.dto.CandidaturaRequest;
import com.teles.candidaturas.api.domain.dto.CandidaturaResponse;
import com.teles.candidaturas.api.endpoint.CandidaturasEndpoint;
import com.teles.candidaturas.api.service.CandidaturaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
public class CandidaturasEndpointTest {

    private static final String URL = "/candidaturas";

    @Mock
    private CandidaturaService service;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new CandidaturasEndpoint(service)).build();
    }

    @Test
    public void should_expect_204_when_there_is_no_data_to_retrieve() throws Exception {
        when(service.findByVagaId(1L)).thenReturn(emptyList());

        mockMvc.perform(get(URL + "/vaga/{vagaId}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_expect_200_when_there_is_data_for_given_id() throws Exception {
        List<CandidaturaResponse> candidaturaResponses = new ArrayList<>();
        candidaturaResponses.add(new CandidaturaResponse());

        when(service.findByVagaId(1L)).thenReturn(candidaturaResponses);

        mockMvc.perform(get(URL + "/vaga/{vagaId}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void should_expect_400_when_vagaId_is_null() throws Exception {

        CandidaturaRequest candidaturaRequest = new CandidaturaRequest();
        candidaturaRequest.setPessoaId(1L);

        mockMvc.perform(post(URL)
                .content(toJson(candidaturaRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_expect_400_when_pessoaId_is_null() throws Exception {

        CandidaturaRequest candidaturaRequest = new CandidaturaRequest();
        candidaturaRequest.setVagaId(1L);

        mockMvc.perform(post(URL)
                .content(toJson(candidaturaRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    private static <T> String toJson(T t) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(t);
    }

}
