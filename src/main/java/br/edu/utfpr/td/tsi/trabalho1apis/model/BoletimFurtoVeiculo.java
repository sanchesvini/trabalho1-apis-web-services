package br.edu.utfpr.td.tsi.trabalho1apis.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity
public class BoletimFurtoVeiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data da ocorrência é obrigatória")
    private Date dataOcorrencia;

    @NotBlank(message = "O período da ocorrência é obrigatório.")
    private String periodoOcorrencia;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "boletim_id")
    @NotEmpty(message = "É necessário informar ao menos uma parte envolvida.")
    @Valid
    private List<Parte> partes;

    @Embedded
    @NotNull(message = "O local da ocorrência é obrigatório.")
    @Valid
    private Endereco localOcorrencia;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "emplacamento.cidade", column = @Column(name = "cidade_emplacamento")),
            @AttributeOverride(name = "emplacamento.estado", column = @Column(name = "estado_emplacamento"))
    })
    @NotNull(message = "O veículo furtado é obrigatório.")
    @Valid
    private Veiculo veiculoFurtado;

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getPeriodoOcorrencia() {
        return periodoOcorrencia;
    }

    public void setPeriodoOcorrencia(String periodoOcorrencia) {
        this.periodoOcorrencia = periodoOcorrencia;
    }

    public List<Parte> getPartes() {
        return partes;
    }

    public void setPartes(List<Parte> partes) {
        this.partes = partes;
    }

    public Endereco getLocalOcorrencia() {
        return localOcorrencia;
    }

    public void setLocalOcorrencia(Endereco localOcorrencia) {
        this.localOcorrencia = localOcorrencia;
    }

    public Veiculo getVeiculoFurtado() {
        return veiculoFurtado;
    }

    public void setVeiculoFurtado(Veiculo veiculoFurtado) {
        this.veiculoFurtado = veiculoFurtado;
    }
}
