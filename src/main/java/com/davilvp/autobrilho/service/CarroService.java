package com.davilvp.autobrilho.service;

import com.davilvp.autobrilho.dto.CarroDTO;
import com.davilvp.autobrilho.model.Carro;
import com.davilvp.autobrilho.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
       carro.setCor(jsonRequisicao.getCor());
       carro.setAno(jsonRequisicao.getAno());
       carro.setStatus(jsonRequisicao.getStatus());


       Carro carrolSalvoNoBanco = carroRepository.save(carro);

       CarroDTO dto = new CarroDTO();
       dto.setId(carrolSalvoNoBanco.getId());
       dto.setModelo(carrolSalvoNoBanco.getModelo());
       dto.setCor(carrolSalvoNoBanco.getCor());
       dto.setStatus(carrolSalvoNoBanco.getStatus());
       dto.setAno(carrolSalvoNoBanco.getAno());
       dto.setMarca(carrolSalvoNoBanco.getMarca());

        return dto;
    }
    public CarroDTO update(CarroDTO carroDaRequisicao){

        Optional<Carro> carroOptional = carroRepository.findById(carroDaRequisicao.getId());
        Carro carroSalvoNoBanco;
        CarroDTO dto = new CarroDTO();
        if(carroOptional.isPresent()) {
            Carro carro = carroOptional.get();
            carro.setMarca(carroDaRequisicao.getMarca());
            carro.setModelo(carroDaRequisicao.getModelo());
            carro.setCor(carroDaRequisicao.getCor());
            carro.setAno(carroDaRequisicao.getAno());
            carro.setStatus(carroDaRequisicao.getStatus());

            carroSalvoNoBanco = carroRepository.save(carro);


            dto.setId(carroSalvoNoBanco.getId());
            dto.setModelo(carroSalvoNoBanco.getModelo());
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
}
