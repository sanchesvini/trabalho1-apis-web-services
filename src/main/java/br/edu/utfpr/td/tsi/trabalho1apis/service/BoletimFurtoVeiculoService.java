package br.edu.utfpr.td.tsi.trabalho1apis.service;

import br.edu.utfpr.td.tsi.trabalho1apis.model.*;
import br.edu.utfpr.td.tsi.trabalho1apis.repository.BoletimFurtoVeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletimFurtoVeiculoService {

    @Autowired
    private BoletimFurtoVeiculoRepository repository;

    @Transactional
    public BoletimFurtoVeiculo registrar(BoletimFurtoVeiculo boletim) {
        return repository.save(boletim);
    }

    public List<BoletimFurtoVeiculo> listarTodos(String cidade, String periodo) {
        if (cidade != null && periodo != null) {
            return repository.findByLocalOcorrenciaCidadeAndPeriodoOcorrenciaContainingIgnoreCase(cidade, periodo);
        } else if (cidade != null) {
            return repository.findByLocalOcorrenciaCidadeContainingIgnoreCase(cidade);
        } else if (periodo != null) {
            return repository.findByPeriodoOcorrenciaContainingIgnoreCase(periodo);
        } else {
            return repository.findAll();
        }
    }

    public BoletimFurtoVeiculo buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Boletim com o ID " + id + " não encontrado"));
    }

    @Transactional
    public BoletimFurtoVeiculo atualizar(Long id, BoletimFurtoVeiculo boletimAtualizado) {
        BoletimFurtoVeiculo boletimExistente = buscarPorId(id);

        boletimExistente.setDataOcorrencia(boletimAtualizado.getDataOcorrencia());
        boletimExistente.setPeriodoOcorrencia(boletimAtualizado.getPeriodoOcorrencia());

        if (boletimAtualizado.getLocalOcorrencia() != null) {
            boletimExistente.setLocalOcorrencia(boletimAtualizado.getLocalOcorrencia());
        }

        if (boletimAtualizado.getVeiculoFurtado() != null) {
            boletimExistente.setVeiculoFurtado(boletimAtualizado.getVeiculoFurtado());
        }

        if (boletimAtualizado.getPartes() != null) {
            boletimExistente.setPartes(boletimAtualizado.getPartes());
        }

        return repository.save(boletimExistente);
    }

    @Transactional
    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
