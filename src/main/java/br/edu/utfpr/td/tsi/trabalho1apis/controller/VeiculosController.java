package br.edu.utfpr.td.tsi.trabalho1apis.controller;

import br.edu.utfpr.td.tsi.trabalho1apis.exceptions.CamposInvalidosException;
import br.edu.utfpr.td.tsi.trabalho1apis.model.Veiculo;
import br.edu.utfpr.td.tsi.trabalho1apis.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    public ResponseEntity<?> registrarVeiculo(@Valid @RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = null;
        try {
            novoVeiculo = service.registrarVeiculo(veiculo);
        } catch (CamposInvalidosException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    @GetMapping
    public ResponseEntity<?> listarTodosVeiculos() {
        List<Veiculo> veiculos = service.listarTodosVeiculos();
        return ResponseEntity.ok(veiculos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarVeiculo(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
        veiculo.setId(id);
        try {
            Veiculo veiculoAtualizado = service.atualizarVeiculo(veiculo);
            return ResponseEntity.ok(veiculoAtualizado);
        } catch (CamposInvalidosException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVeiculo(@PathVariable Long id) {
        try {
            service.deletarVeiculo(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado.");
        }
    }


}
