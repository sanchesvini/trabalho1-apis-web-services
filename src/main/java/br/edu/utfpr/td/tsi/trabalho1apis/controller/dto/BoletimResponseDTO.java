package br.edu.utfpr.td.tsi.trabalho1apis.controller.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

public class BoletimResponseDTO {

    private LocalDate dataOcorrencia;
    private String periodoOcorrencia;
    private EnderecoDTO localOcorrencia;
    private VeiculoDTO veiculoFurtado;

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
}
