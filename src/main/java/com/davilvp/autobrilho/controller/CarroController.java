package com.davilvp.autobrilho.controller;

import com.davilvp.autobrilho.dto.CarroDTO;
import com.davilvp.autobrilho.dto.RelatorioResponse;
import com.davilvp.autobrilho.model.Carro;
import com.davilvp.autobrilho.model.Status;
import com.davilvp.autobrilho.service.CarroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
public class CarroController {
   private final CarroService service;

    @PostMapping
    public ResponseEntity<CarroDTO> create(@RequestBody CarroDTO jsonRequisicao){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(jsonRequisicao));
    }

    @PutMapping
    public ResponseEntity<CarroDTO> update(@RequestBody CarroDTO jsonRequisicao){
        log.info("Carro entrando na aplicação // controller // marca: {}",jsonRequisicao.getMarca());
        log.info("Carro entrando na aplicação // controller // placa: {}",jsonRequisicao.getPlaca());
        log.info("Carro entrando na aplicação // controller // modelo: {}",jsonRequisicao.getModelo());
        log.info("Carro entrando na aplicação // controller // ano: {}",jsonRequisicao.getAno());
        log.info("Carro entrando na aplicação // controller // cor: {}",jsonRequisicao.getCor());
        log.info("Carro entrando na aplicação // controller // status {}:",jsonRequisicao.getStatus());

        CarroDTO  carroAtualizado = service.update(jsonRequisicao);

        return ResponseEntity.ok(carroAtualizado);
    }

    @GetMapping
    public ResponseEntity<List <CarroDTO>> getTodosCarros(@RequestParam(required = false) Status status) {
        List<CarroDTO> carrosRetornados;
        if (status != null){
            carrosRetornados = service.buscarCarrosPorStatus(status);

        } else {
            carrosRetornados = service.buscarTodosCarros();
        }
        return ResponseEntity.ok(carrosRetornados);
    }

    @GetMapping("/relatorio")
    public ResponseEntity<RelatorioResponse> relatorio(){
        RelatorioResponse relatorio = service.relatorio();
        return ResponseEntity.ok(relatorio);
    }
}
