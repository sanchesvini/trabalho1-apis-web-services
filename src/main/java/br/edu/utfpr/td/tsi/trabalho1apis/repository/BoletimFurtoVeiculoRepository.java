package br.edu.utfpr.td.tsi.trabalho1apis.repository;

import br.edu.utfpr.td.tsi.trabalho1apis.model.BoletimFurtoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoletimFurtoVeiculoRepository extends JpaRepository<BoletimFurtoVeiculo, Long> {

    List<BoletimFurtoVeiculo> findByLocalOcorrenciaCidadeAndPeriodoOcorrencia(String cidade, String periodo);

    List<BoletimFurtoVeiculo> findByLocalOcorrenciaCidade(String cidade);

    List<BoletimFurtoVeiculo> findByPeriodoOcorrencia(String periodo);
}
