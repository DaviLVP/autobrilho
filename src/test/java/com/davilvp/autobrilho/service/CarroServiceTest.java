package com.davilvp.autobrilho.service;

import com.davilvp.autobrilho.dto.CarroDTO;
import com.davilvp.autobrilho.mapper.CarroMapper;
import com.davilvp.autobrilho.model.Carro;
import com.davilvp.autobrilho.model.Status;
import com.davilvp.autobrilho.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CarroServiceTest {

    @Mock
    private CarroRepository carroRepository;

    @Mock
    private CarroMapper carroMapper;
    @InjectMocks
    private CarroService carroService;

    @Test
    public void deveCriarComSucessoUmCarroQuandoReceberDadosValidos(){
        CarroDTO carroDTO = new CarroDTO();
        carroDTO.setMarca("BMW");
        carroDTO.setModelo("i320");
        carroDTO.setPlaca("IJX-3J46");
        carroDTO.setAno("2022");
        carroDTO.setCor("Branca");
        carroDTO.setStatus(Status.DISPONIVEL);

        CarroDTO carroDTOComId = new CarroDTO();
        carroDTOComId.setId(1L);
        carroDTOComId.setMarca("BMW");
        carroDTOComId.setModelo("i320");
        carroDTOComId.setPlaca("IJX-3J46");
        carroDTOComId.setAno("2022");
        carroDTOComId.setCor("Branca");
        carroDTOComId.setStatus(Status.DISPONIVEL);

        Carro carro = new Carro();
        carro.setId(1L);
        carro.setMarca("BMW");
        carro.setModelo("i320");
        carro.setPlaca("IJX-3J46");
        carro.setAno("2022");
        carro.setCor("Branca");
        carro.setStatus(Status.DISPONIVEL);

        Carro carroSemId = new Carro();
        carroSemId.setId(1L);
        carroSemId.setMarca("BMW");
        carroSemId.setModelo("i320");
        carroSemId.setPlaca("IJX-3J46");
        carroSemId.setAno("2022");
        carroSemId.setCor("Branca");
        carroSemId.setStatus(Status.DISPONIVEL);

        when(carroRepository.save(any(Carro.class))).thenReturn(carro);
        when(carroMapper.toEntity(any(CarroDTO.class))).thenReturn(carroSemId);
        when(carroMapper.toDTO(any(Carro.class))).thenReturn(carroDTOComId);

        // Execução
        CarroDTO resultado = carroService.create(carroDTO);

        // Asserção
        assertEquals(1L, resultado.getId());
    }

}
