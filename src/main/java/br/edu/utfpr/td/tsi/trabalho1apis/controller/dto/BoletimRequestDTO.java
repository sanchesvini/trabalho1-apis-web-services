package br.edu.utfpr.td.tsi.trabalho1apis.controller.dto;

import br.edu.utfpr.td.tsi.trabalho1apis.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoletimRequestDTO {

    @NotNull(message = "A data da ocorrência é obrigatória.")

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataOcorrencia;

    @NotBlank(message = "O período da ocorrência é obrigatório.")
    private String periodoOcorrencia;

    @NotNull(message = "O local da ocorrência é obrigatório.")
    @Valid
    private EnderecoDTO localOcorrencia;

    @NotNull(message = "O veículo furtado é obrigatório.")
    @Valid
    private VeiculoDTO veiculoFurtado;

    @Valid
    private List<ParteDTO> partes;

    public BoletimFurtoVeiculo toEntity() {
        BoletimFurtoVeiculo boletim = new BoletimFurtoVeiculo();

        boletim.setDataOcorrencia(this.dataOcorrencia);
        boletim.setPeriodoOcorrencia(this.periodoOcorrencia);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(this.localOcorrencia.getLogradouro());
        endereco.setNumero(this.localOcorrencia.getNumero());
        endereco.setBairro(this.localOcorrencia.getBairro());
        endereco.setCidade(this.localOcorrencia.getCidade());
        endereco.setEstado(this.localOcorrencia.getEstado());
        boletim.setLocalOcorrencia(endereco);

        Veiculo veiculo = new Veiculo();
        veiculo.setAnoFabricacao(this.veiculoFurtado.getAnoFabricacao());
        veiculo.setCor(this.veiculoFurtado.getCor());
        veiculo.setMarca(this.veiculoFurtado.getMarca());
        veiculo.setTipoVeiculo(this.veiculoFurtado.getTipoVeiculo());

        Emplacamento emplacamento = new Emplacamento();
        emplacamento.setPlaca(this.veiculoFurtado.getEmplacamento().getPlaca());
        emplacamento.setEstado(this.veiculoFurtado.getEmplacamento().getEstado());
        emplacamento.setCidade(this.veiculoFurtado.getEmplacamento().getCidade());
        veiculo.setEmplacamento(emplacamento);

        boletim.setVeiculoFurtado(veiculo);

        if (this.partes != null && !this.partes.isEmpty()) {
            List<Parte> listaPartes = new ArrayList<>();
            for (ParteDTO parteDTO : this.partes) {
                Parte parte = new Parte();
                parte.setNome(parteDTO.getNome());
                parte.setEmail(parteDTO.getEmail());
                parte.setTelefone(parteDTO.getTelefone());
                parte.setTipoEnvolvimento(parteDTO.getTipoEnvolvimento());
                parte.setBoletim(boletim);
                listaPartes.add(parte);
            }
            boletim.setPartes(listaPartes);
        }

        return boletim;
    }


    public LocalDate getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(LocalDate dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getPeriodoOcorrencia() {
        return periodoOcorrencia;
    }

    public void setPeriodoOcorrencia(String periodoOcorrencia) {
        this.periodoOcorrencia = periodoOcorrencia;
    }

    public EnderecoDTO getLocalOcorrencia() {
        return localOcorrencia;
    }

    public void setLocalOcorrencia(EnderecoDTO localOcorrencia) {
        this.localOcorrencia = localOcorrencia;
    }

    public VeiculoDTO getVeiculoFurtado() {
        return veiculoFurtado;
    }

    public void setVeiculoFurtado(VeiculoDTO veiculoFurtado) {
        this.veiculoFurtado = veiculoFurtado;
    }

    public List<ParteDTO> getPartes() {
        return partes;
    }

    public void setPartes(List<ParteDTO> partes) {
        this.partes = partes;
    }
}
