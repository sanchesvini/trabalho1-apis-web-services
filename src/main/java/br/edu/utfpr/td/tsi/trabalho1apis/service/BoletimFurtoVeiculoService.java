package br.edu.utfpr.td.tsi.trabalho1apis.service;

import br.edu.utfpr.td.tsi.trabalho1apis.model.BoletimFurtoVeiculo;
import br.edu.utfpr.td.tsi.trabalho1apis.repository.BoletimFurtoVeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletimFurtoVeiculoService {

    @Autowired
    private BoletimFurtoVeiculoRepository repository;

    public BoletimFurtoVeiculo registrar(BoletimFurtoVeiculo boletim) {
        return repository.save(boletim);
    }

    public List<BoletimFurtoVeiculo> listarTodos(String cidade, String periodo) {
        if (cidade != null && periodo != null) {
            return repository.findByLocalOcorrenciaCidadeAndPeriodoOcorrencia(cidade, periodo);
        } else if (cidade != null) {
            return repository.findByLocalOcorrenciaCidade(cidade);
        } else if (periodo != null) {
            return repository.findByPeriodoOcorrencia(periodo);
        } else {
            return repository.findAll();
        }
    }

    public BoletimFurtoVeiculo buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Boletim com o ID " + id + " não encontrado"));
    }

    public BoletimFurtoVeiculo atualizar(Long id, BoletimFurtoVeiculo boletim) {
        BoletimFurtoVeiculo boletimExistente = buscarPorId(id);
        BeanUtils.copyProperties(boletim, boletimExistente, "id");
        return repository.save(boletimExistente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
