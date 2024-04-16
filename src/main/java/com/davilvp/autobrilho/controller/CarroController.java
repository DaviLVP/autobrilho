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


        return ResponseEntity.ok(service.update(jsonRequisicao));
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
