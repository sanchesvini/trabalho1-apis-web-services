package br.edu.utfpr.td.tsi.trabalho1apis.repository;

import br.edu.utfpr.td.tsi.trabalho1apis.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
