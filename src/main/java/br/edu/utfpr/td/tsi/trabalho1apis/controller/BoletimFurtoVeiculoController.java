package br.edu.utfpr.td.tsi.trabalho1apis.controller;

import br.edu.utfpr.td.tsi.trabalho1apis.model.BoletimFurtoVeiculo;
import br.edu.utfpr.td.tsi.trabalho1apis.service.BoletimFurtoVeiculoService;
import br.edu.utfpr.td.tsi.trabalho1apis.service.CsvService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/boletins")
public class BoletimFurtoVeiculoController {

    @Autowired
    private BoletimFurtoVeiculoService service;

    @Autowired
    private CsvService csvService;

    @PostMapping
    public ResponseEntity<BoletimFurtoVeiculo> registrarBoletim(@Valid @RequestBody BoletimFurtoVeiculo boletim) {
        BoletimFurtoVeiculo novoBoletim = service.registrar(boletim);
        return new ResponseEntity<>(novoBoletim, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoletimFurtoVeiculo>> listarTodosBoletins( @RequestParam(required = false) String cidade, @RequestParam(required = false) String periodo) {
        List<BoletimFurtoVeiculo> boletins = service.listarTodos(cidade, periodo);
        return ResponseEntity.ok(boletins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletimFurtoVeiculo> buscarBoletimPorId(@PathVariable Long id) {
        try {
            BoletimFurtoVeiculo boletim = service.buscarPorId(id);
            return ResponseEntity.ok(boletim);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoletimFurtoVeiculo> atualizarBoletim(@PathVariable Long id, @Valid @RequestBody BoletimFurtoVeiculo boletim) {
        try {
            BoletimFurtoVeiculo boletimAtualizado = service.atualizar(id, boletim);
            return ResponseEntity.ok(boletimAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarBoletim(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/csv")
    public ResponseEntity<List<BoletimFurtoVeiculo>> registrarBoletim() {
        List<BoletimFurtoVeiculo> boletins;
        try{
            boletins = csvService.lerArquivo();
            return new ResponseEntity<>(boletins, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }

    }


}
