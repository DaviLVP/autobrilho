package com.davilvp.autobrilho.controller;

import com.davilvp.autobrilho.dto.CarroDTO;
import com.davilvp.autobrilho.model.Carro;
import com.davilvp.autobrilho.model.Status;
import com.davilvp.autobrilho.service.CarroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarroController.class)
class CarroControllerTeste {
   @Autowired
    private MockMvc mockMvc;
   @Autowired
   private ObjectMapper objectMapper;
   @MockBean
   private CarroService carroService;
   @Test
    void deveLancarExcecao422QuandoTentarCadastrarVeiculoComPlacaDuplicada() throws Exception {
        CarroDTO carro = new CarroDTO();
        carro.setModelo("i320");
        carro.setPlaca("IJX-3J46");
        carro.setMarca("BMW");
        carro.setAno("2022");
        carro.setCor("Branca");
        carro.setStatus(Status.DISPONIVEL);

       CarroDTO carroRetorno = new CarroDTO();
       carroRetorno.setModelo("i320");
       carroRetorno.setPlaca("IJX-3J46");
       carroRetorno.setMarca("BMW");
       carroRetorno.setAno("2022");
       carroRetorno.setCor("Branca");
       carroRetorno.setId(1L);
       carroRetorno.setStatus(Status.DISPONIVEL);


       String carroJson = objectMapper.writeValueAsString(carro);

        when(carroService.create(any(CarroDTO.class))).thenReturn(carroRetorno);

        mockMvc.perform(post("/carros").content(carroJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        doThrow(HttpClientErrorException.UnprocessableEntity.class).when(carroService).create(any(CarroDTO.class));
       mockMvc.perform(post("/carros").content(carroJson).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isUnprocessableEntity());
    }
}
