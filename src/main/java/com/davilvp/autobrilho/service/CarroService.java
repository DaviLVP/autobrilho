package com.davilvp.autobrilho.service;

import com.davilvp.autobrilho.dto.CarroDTO;
import com.davilvp.autobrilho.model.Carro;
import com.davilvp.autobrilho.model.Status;
import com.davilvp.autobrilho.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarroService {
    private final CarroRepository carroRepository;

    public CarroDTO create(CarroDTO jsonRequisicao){
       Carro carro = new Carro();
       carro.setMarca(jsonRequisicao.getMarca());
       carro.setModelo(jsonRequisicao.getModelo());
       carro.setPlaca(jsonRequisicao.getPlaca());
       carro.setCor(jsonRequisicao.getCor());
       carro.setAno(jsonRequisicao.getAno());
       carro.setStatus(jsonRequisicao.getStatus());


       Carro carrolSalvoNoBanco = carroRepository.save(carro);

       CarroDTO dto = new CarroDTO();
       dto.setId(carrolSalvoNoBanco.getId());
       dto.setModelo(carrolSalvoNoBanco.getModelo());
       dto.setPlaca(jsonRequisicao.getPlaca());
       dto.setCor(carrolSalvoNoBanco.getCor());
       dto.setStatus(carrolSalvoNoBanco.getStatus());
       dto.setAno(carrolSalvoNoBanco.getAno());
       dto.setMarca(carrolSalvoNoBanco.getMarca());

        return dto;
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
}
