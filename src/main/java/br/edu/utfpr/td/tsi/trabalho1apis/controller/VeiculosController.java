package br.edu.utfpr.td.tsi.trabalho1apis.controller;

import br.edu.utfpr.td.tsi.trabalho1apis.model.Veiculo;
import br.edu.utfpr.td.tsi.trabalho1apis.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculoService service;

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarTodosVeiculos(@RequestParam(required = false) String placa, @RequestParam(required = false) String cor, @RequestParam(required = false) String tipoVeiculo) {
        List<Veiculo> veiculos = service.listarTodosVeiculos(placa, cor, tipoVeiculo);
        return ResponseEntity.ok(veiculos);
    }
}
