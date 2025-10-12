package br.edu.utfpr.td.tsi.trabalho1apis.repository;

import br.edu.utfpr.td.tsi.trabalho1apis.model.BoletimFurtoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletimFurtoVeiculoRepository extends JpaRepository<BoletimFurtoVeiculo, Long> {

    List<BoletimFurtoVeiculo> findByLocalOcorrenciaCidadeAndPeriodoOcorrenciaContainingIgnoreCase(String cidade, String periodo);

    List<BoletimFurtoVeiculo> findByLocalOcorrenciaCidadeContainingIgnoreCase(String cidade);

    List<BoletimFurtoVeiculo> findByPeriodoOcorrenciaContainingIgnoreCase(String periodo);

    List<BoletimFurtoVeiculo> findByVeiculoFurtadoEmplacamentoPlacaContainingIgnoreCase(String placa);

    List<BoletimFurtoVeiculo> findByVeiculoFurtadoCorContainingIgnoreCase(String cor);

    List<BoletimFurtoVeiculo> findByVeiculoFurtadoTipoVeiculoContainingIgnoreCase(String tipoVeiculo);

}
