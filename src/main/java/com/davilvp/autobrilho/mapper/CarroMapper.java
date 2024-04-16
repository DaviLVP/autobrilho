package com.davilvp.autobrilho.mapper;

import com.davilvp.autobrilho.dto.CarroDTO;
import com.davilvp.autobrilho.model.Carro;
import org.springframework.stereotype.Component;

@Component
public class CarroMapper {

    public Carro toEntity(CarroDTO source){
        Carro destination = new Carro();
        destination.setMarca(source.getMarca());
        destination.setModelo(source.getModelo());
        destination.setPlaca(source.getPlaca());
        destination.setCor(source.getCor());
        destination.setAno(source.getAno());
        destination.setStatus(source.getStatus());

        return destination;
    }
    public CarroDTO toDTO(Carro source){

        CarroDTO destination = new CarroDTO();
        destination.setId(source.getId());
        destination.setModelo(source.getModelo());
        destination.setPlaca(source.getPlaca());
        destination.setCor(source.getCor());
        destination.setStatus(source.getStatus());
        destination.setAno(source.getAno());
        destination.setMarca(source.getMarca());
        return destination;
    }

    public Carro updateEntity(CarroDTO source, Carro destination){
        destination.setModelo(source.getModelo());
        destination.setPlaca(source.getPlaca());
        destination.setCor(source.getCor());
        destination.setStatus(source.getStatus());
        destination.setAno(source.getAno());
        destination.setMarca(source.getMarca());
        return destination;
    }

}
