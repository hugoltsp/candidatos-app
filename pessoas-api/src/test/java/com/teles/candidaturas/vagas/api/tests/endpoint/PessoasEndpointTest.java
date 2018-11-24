package com.teles.candidaturas.pessoas.api.tests.endpoint;

import com.teles.candidaturas.pessoas.api.domain.dto.PessoaResponse;
import com.teles.candidaturas.pessoas.api.endpoint.PessoasEndpoint;
import com.teles.candidaturas.pessoas.api.service.PessoaService;
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
public class PessoasEndpointTest {

    private static final String URL = "/pessoas";

    @Mock
    private PessoaService pessoaService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new PessoasEndpoint(pessoaService)).build();
    }

    @Test
    public void should_expect_204_when_there_is_no_data_to_retrieve() throws Exception {
        when(pessoaService.findById(1L)).thenReturn(empty());

        mockMvc.perform(get(URL + "/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_expect_200_when_there_is_data_for_given_id() throws Exception {
        when(pessoaService.findById(1L)).thenReturn(of(new PessoaResponse()));

        mockMvc.perform(get(URL + "/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void should_expect_200_when_request_is_valid() throws Exception {

        String json = "{\"nome\":\"Jhon\"," +
                "\"profissao\":\"Programmer\"," +
                "\"localizacao\":\"A\"," +
                "\"nivel\":\"3\"}";

        mockMvc.perform(post(URL)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void should_expect_400_when_name_is_blank() throws Exception {

        String json = "{\"nome\":null," +
                "\"profissao\":\"Programmer\"," +
                "\"localizacao\":\"A\"," +
                "\"nivel\":\"3\"}";

        mockMvc.perform(post(URL)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_expect_400_when_profissao_is_blank() throws Exception {

        String json = "{\"nome\":\"Jhon\"," +
                "\"profissao\":null," +
                "\"localizacao\":\"A\"," +
                "\"nivel\":\"3\"}";

        mockMvc.perform(post(URL)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_expect_400_when_localizacao_is_null() throws Exception {

        String json = "{\"nome\":\"Jhon\"," +
                "\"profissao\":\"Programmer\"," +
                "\"localizacao\":null," +
                "\"nivel\":\"3\"}";

        mockMvc.perform(post(URL)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_expect_400_when_nivel_is_null() throws Exception {

        String json = "{\"nome\":\"Jhon\"," +
                "\"profissao\":\"Programmer\"," +
                "\"localizacao\":\"A\"," +
                "\"nivel\":null}";

        mockMvc.perform(post(URL)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}
