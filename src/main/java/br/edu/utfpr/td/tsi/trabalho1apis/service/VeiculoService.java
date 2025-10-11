package br.edu.utfpr.td.tsi.trabalho1apis.service;

import br.edu.utfpr.td.tsi.trabalho1apis.exceptions.CamposInvalidosException;
import br.edu.utfpr.td.tsi.trabalho1apis.model.BoletimFurtoVeiculo;
import br.edu.utfpr.td.tsi.trabalho1apis.model.Veiculo;
import br.edu.utfpr.td.tsi.trabalho1apis.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo registrarVeiculo(Veiculo veiculo) throws CamposInvalidosException {
        if(veiculo.getEmplacamento() == null || veiculo.getMarca() == null || veiculo.getTipoVeiculo() == null || (veiculo.getAnoFabricacao() <= new Date().getYear()) || veiculo.getCor() == null){
            throw new CamposInvalidosException("Campos inválidos ao registrar veículo.");
        }
        return veiculoRepository.save(veiculo);
    }
    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veiculo com o ID " + id + " não encontrado"));
    }

    public List<Veiculo> listarTodosVeiculos() {
        return veiculoRepository.findAll();
    }

    public Veiculo atualizarVeiculo(@Valid Veiculo veiculo) throws CamposInvalidosException {
        buscarPorId(veiculo.getId());
        if(veiculo.getEmplacamento() == null || veiculo.getMarca() == null || veiculo.getTipoVeiculo() == null || (veiculo.getAnoFabricacao() <= new Date().getYear()) || veiculo.getCor() == null){
            throw new CamposInvalidosException("Campos inválidos ao registrar veículo.");
        }
        return veiculoRepository.save(veiculo);
    }

    public void deletarVeiculo(Long id) {
        buscarPorId(id);
        veiculoRepository.deleteById(id);
    }
}
