package com.davilvp.autobrilho.service;

import com.davilvp.autobrilho.dto.CarroDTO;
import com.davilvp.autobrilho.dto.RelatorioResponse;
import com.davilvp.autobrilho.mapper.CarroMapper;
import com.davilvp.autobrilho.model.Carro;
import com.davilvp.autobrilho.model.Status;
import com.davilvp.autobrilho.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarroService {
    private final CarroRepository carroRepository;
    private final CarroMapper carroMapper;

    public CarroDTO create(CarroDTO carroDaRequisicao){
        Carro carro = carroMapper.toEntity(carroDaRequisicao);

       return carroMapper.toDTO(carroRepository.save(carro));
    }
    public CarroDTO update(CarroDTO carroDaRequisicao){

        Optional<Carro> carroOptional = carroRepository.findByPlaca(carroDaRequisicao.getPlaca());
        Carro carroSalvoNoBanco;
        CarroDTO dto = new CarroDTO();
        if(carroOptional.isPresent()) {
            Carro carro = carroOptional.get();
            carro.setMarca(carroDaRequisicao.getMarca());
            carro.setModelo(carroDaRequisicao.getModelo());
            carro.setPlaca(carroDaRequisicao.getPlaca());
            carro.setCor(carroDaRequisicao.getCor());
            carro.setAno(carroDaRequisicao.getAno());
            carro.setStatus(carroDaRequisicao.getStatus());

            carroSalvoNoBanco = carroRepository.save(carro);


            dto.setId(carroSalvoNoBanco.getId());
            dto.setModelo(carroSalvoNoBanco.getModelo());
            dto.setPlaca(carroSalvoNoBanco.getPlaca());
            dto.setCor(carroSalvoNoBanco.getCor());
            dto.setStatus(carroSalvoNoBanco.getStatus());
            dto.setAno(carroSalvoNoBanco.getAno());
            dto.setMarca(carroSalvoNoBanco.getMarca());

            return dto;
        }   else {
            log.error("carro nao encontrado");
        }

        return dto;
    }

    public List<CarroDTO> buscarTodosCarros() {
       List<Carro> carros = carroRepository.findAll();
       List<CarroDTO> carrosDTO = new ArrayList<>();
       for (Carro carro : carros){

           CarroDTO carroDTO = new CarroDTO();

           carroDTO.setId(carro.getId());
           carroDTO.setModelo(carro.getModelo());
           carroDTO.setPlaca(carro.getPlaca());
           carroDTO.setCor(carro.getCor());
           carroDTO.setStatus(carro.getStatus());
           carroDTO.setAno(carro.getAno());
           carroDTO.setMarca(carro.getMarca());

           carrosDTO.add(carroDTO);
       }
       return carrosDTO;
    }

    public List<CarroDTO> buscarCarrosPorStatus(Status status) {
        List<Carro> carros = carroRepository.findByStatus(status);
        List<CarroDTO> carrosDTO = new ArrayList<>();
        for (Carro carro : carros){

            CarroDTO carroDTO = new CarroDTO();

            carroDTO.setId(carro.getId());
            carroDTO.setModelo(carro.getModelo());
            carroDTO.setPlaca(carro.getPlaca());
            carroDTO.setCor(carro.getCor());
            carroDTO.setStatus(carro.getStatus());
            carroDTO.setAno(carro.getAno());
            carroDTO.setMarca(carro.getMarca());

            carrosDTO.add(carroDTO);
        }
        return carrosDTO;
    }

    public RelatorioResponse relatorio() {
        List<Carro> carros = carroRepository.findAll();
        Map<Status, Integer> contadorRelatorio = new HashMap<>();

        for(Carro carro : carros){
            contadorRelatorio.put(carro.getStatus(), contadorRelatorio.getOrDefault(carro.getStatus(), 0)+1);

        }
        RelatorioResponse relatorio = new RelatorioResponse();
        for (Map.Entry<Status, Integer> entry : contadorRelatorio.entrySet()) {
            relatorio.getRelatorio().add(entry.getKey() + ": " + entry.getValue());
        }
        return relatorio;
    }
}
