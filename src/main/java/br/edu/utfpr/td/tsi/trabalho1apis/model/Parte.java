package br.edu.utfpr.td.tsi.trabalho1apis.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Parte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @JsonIgnore
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    @JsonIgnore
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    @JsonIgnore
    private String telefone;

    @NotBlank(message = "O tipo de envolvimento é obrigatório.")
    private String tipoEnvolvimento;

    @ManyToOne
    @JoinColumn(name = "boletim_id")
    @JsonIgnore
    private BoletimFurtoVeiculo boletim;

    @JsonCreator
    public Parte(@JsonProperty("nome") String nome,
                 @JsonProperty("email") String email,
                 @JsonProperty("telefone") String telefone,
                 @JsonProperty("tipoEnvolvimento") String tipoEnvolvimento) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipoEnvolvimento = tipoEnvolvimento;
    }

    public Parte() {
    }

    public Long getId() {
        return id;
    }

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

    public BoletimFurtoVeiculo getBoletim() {
        return boletim;
    }

    public void setBoletim(BoletimFurtoVeiculo boletim) {
        this.boletim = boletim;
    }
}