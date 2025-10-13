package br.edu.utfpr.td.tsi.trabalho1apis.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ParteDTO {

    private String nome;

    @Email(message = "Formato de e-mail inválido.")
    private String email;

    private String telefone;

    @NotBlank(message = "O tipo de envolvimento é obrigatório.")
    private String tipoEnvolvimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoEnvolvimento() {
        return tipoEnvolvimento;
    }

    public void setTipoEnvolvimento(String tipoEnvolvimento) {
        this.tipoEnvolvimento = tipoEnvolvimento;
    }
}
