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

    @Transactional
    public BoletimFurtoVeiculo atualizar(Long id, BoletimFurtoVeiculo boletim) {
        BoletimFurtoVeiculo boletimExistente = buscarPorId(id);

        boletimExistente.setDataOcorrencia(boletim.getDataOcorrencia());
        boletimExistente.setPeriodoOcorrencia(boletim.getPeriodoOcorrencia());

        if (boletim.getLocalOcorrencia() != null) {
            Endereco enderecoExistente = boletimExistente.getLocalOcorrencia();
            Endereco enderecoAtualizacao = boletim.getLocalOcorrencia();
            enderecoExistente.setLogradouro(enderecoAtualizacao.getLogradouro());
            enderecoExistente.setNumero(enderecoAtualizacao.getNumero());
            enderecoExistente.setBairro(enderecoAtualizacao.getBairro());
            enderecoExistente.setCidade(enderecoAtualizacao.getCidade());
            enderecoExistente.setEstado(enderecoAtualizacao.getEstado());
        }

        if (boletim.getVeiculoFurtado() != null) {
            Veiculo veiculoExistente = boletimExistente.getVeiculoFurtado();
            Veiculo veiculoAtualizacao = boletim.getVeiculoFurtado();

            veiculoExistente.setAnoFabricacao(veiculoAtualizacao.getAnoFabricacao());
            veiculoExistente.setCor(veiculoAtualizacao.getCor());
            veiculoExistente.setMarca(veiculoAtualizacao.getMarca());
            veiculoExistente.setTipoVeiculo(veiculoAtualizacao.getTipoVeiculo());

            if (veiculoAtualizacao.getEmplacamento() != null) {
                Emplacamento emplacamentoExistente = veiculoExistente.getEmplacamento();
                Emplacamento emplacamentoAtualizacao = veiculoAtualizacao.getEmplacamento();

                emplacamentoExistente.setPlaca(emplacamentoAtualizacao.getPlaca());
                emplacamentoExistente.setCidade(emplacamentoAtualizacao.getCidade());
                emplacamentoExistente.setEstado(emplacamentoAtualizacao.getEstado());
            }
        }

        if (boletim.getPartes() != null) {
            boletimExistente.getPartes().clear();
            boletimExistente.getPartes().addAll(boletim.getPartes());
            for (Parte parte : boletimExistente.getPartes()) {
                parte.setBoletim(boletimExistente);
            }
        }
        return repository.save(boletimExistente);
    }

    @Transactional
    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
