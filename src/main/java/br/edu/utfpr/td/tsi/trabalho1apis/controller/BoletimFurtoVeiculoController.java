package br.edu.utfpr.td.tsi.trabalho1apis.controller;

import br.edu.utfpr.td.tsi.trabalho1apis.controller.dto.*;
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
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/boletins")
public class BoletimFurtoVeiculoController {

    @Autowired
    private BoletimFurtoVeiculoService service;

    @Autowired
    private CsvService csvService;

    @PostMapping
    public ResponseEntity<BoletimFurtoVeiculo> registrarBoletim(@Valid @RequestBody BoletimRequestDTO boletimDTO) {
        BoletimFurtoVeiculo novoBoletim = service.registrar(boletimDTO.toEntity());
        return new ResponseEntity<>(novoBoletim, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoletimResponseDTO>> listarTodosBoletins(@RequestParam(required = false) String cidade, @RequestParam(required = false) String periodo) {
        List<BoletimFurtoVeiculo> boletins = service.listarTodos(cidade, periodo);
        List<BoletimResponseDTO> boletinsDTO = boletins.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(boletinsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletimResponseDTO> buscarBoletimPorId(@PathVariable Long id) {
        try {
            BoletimFurtoVeiculo boletim = service.buscarPorId(id);
            return ResponseEntity.ok(toResponseDTO(boletim));
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

    private BoletimResponseDTO toResponseDTO(BoletimFurtoVeiculo boletim) {
        BoletimResponseDTO dto = new BoletimResponseDTO();
        dto.setDataOcorrencia(boletim.getDataOcorrencia());
        dto.setPeriodoOcorrencia(boletim.getPeriodoOcorrencia());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro(boletim.getLocalOcorrencia().getLogradouro());
        enderecoDTO.setNumero(boletim.getLocalOcorrencia().getNumero());
        enderecoDTO.setBairro(boletim.getLocalOcorrencia().getBairro());
        enderecoDTO.setCidade(boletim.getLocalOcorrencia().getCidade());
        enderecoDTO.setEstado(boletim.getLocalOcorrencia().getEstado());
        dto.setLocalOcorrencia(enderecoDTO);


        VeiculoDTO veiculoDTO = new VeiculoDTO();

        veiculoDTO.setAnoFabricacao(boletim.getVeiculoFurtado().getAnoFabricacao());
        veiculoDTO.setCor(boletim.getVeiculoFurtado().getCor());
        veiculoDTO.setMarca(boletim.getVeiculoFurtado().getMarca());
        veiculoDTO.setTipoVeiculo(boletim.getVeiculoFurtado().getTipoVeiculo());

        EmplacamentoDTO emplacamentoDTO = new EmplacamentoDTO();
        emplacamentoDTO.setPlaca(boletim.getVeiculoFurtado().getEmplacamento().getPlaca());
        emplacamentoDTO.setEstado(boletim.getVeiculoFurtado().getEmplacamento().getEstado());
        emplacamentoDTO.setCidade(boletim.getVeiculoFurtado().getEmplacamento().getCidade());
        veiculoDTO.setEmplacamento(emplacamentoDTO);

        dto.setVeiculoFurtado(veiculoDTO);

        return dto;
    }


}
