package br.edu.utfpr.td.tsi.trabalho1apis.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1900, message = "O ano de fabricação deve ser válido.")
    @Max(value = 2026, message = "O ano de fabricação deve ser válido.")
    private int anoFabricacao;

    @NotBlank(message = "A cor do veículo é obrigatória.")
    private String cor;

    @NotBlank(message = "A marca do veículo é obrigatória.")
    private String marca;

    @NotBlank(message = "O tipo do veículo é obrigatório.")
    private String tipoVeiculo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "emplacamento_id", referencedColumnName = "id")
    @NotNull(message = "Os dados de emplacamento são obrigatórios.")
    @Valid
    private Emplacamento emplacamento;

    public Long getId() {
        return id;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public Emplacamento getEmplacamento() {
        return emplacamento;
    }

    public void setEmplacamento(Emplacamento emplacamento) {
        this.emplacamento = emplacamento;
    }
}
