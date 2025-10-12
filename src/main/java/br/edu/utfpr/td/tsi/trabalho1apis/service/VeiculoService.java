package br.edu.utfpr.td.tsi.trabalho1apis.service;

import br.edu.utfpr.td.tsi.trabalho1apis.model.BoletimFurtoVeiculo;
import br.edu.utfpr.td.tsi.trabalho1apis.model.Veiculo;
import br.edu.utfpr.td.tsi.trabalho1apis.repository.BoletimFurtoVeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private BoletimFurtoVeiculoRepository boletimRepository;

    public List<Veiculo> listarTodosVeiculos(String placa, String cor, String tipoVeiculo) {
        List<BoletimFurtoVeiculo> boletins;

        if (placa != null) {
            boletins = boletimRepository.findByVeiculoFurtadoEmplacamentoPlacaContainingIgnoreCase(placa);
        } else if (cor != null) {
            boletins = boletimRepository.findByVeiculoFurtadoCorContainingIgnoreCase(cor);
        } else if (tipoVeiculo != null) {
            boletins = boletimRepository.findByVeiculoFurtadoTipoVeiculoContainingIgnoreCase(tipoVeiculo);
        } else {
            boletins = boletimRepository.findAll();
        }

        return boletins.stream().map(BoletimFurtoVeiculo::getVeiculoFurtado).collect(Collectors.toList());
    }
}
