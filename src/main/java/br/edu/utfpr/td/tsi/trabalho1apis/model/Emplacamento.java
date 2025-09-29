package br.edu.utfpr.td.tsi.trabalho1apis.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Emplacamento {

    @NotBlank(message = "A placa é obrigatória.")
    private String placa;

    @NotBlank(message = "O estado do emplacamento é obrigatório.")
    private String estado;

    @NotBlank(message = "A cidade do emplacamento é obrigatória.")
    private String cidade;


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
