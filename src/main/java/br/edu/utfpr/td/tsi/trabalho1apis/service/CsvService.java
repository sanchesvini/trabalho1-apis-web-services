package br.edu.utfpr.td.tsi.trabalho1apis.service;

import br.edu.utfpr.td.tsi.trabalho1apis.model.BoletimFurtoVeiculo;
import br.edu.utfpr.td.tsi.trabalho1apis.model.Emplacamento;
import br.edu.utfpr.td.tsi.trabalho1apis.model.Endereco;
import br.edu.utfpr.td.tsi.trabalho1apis.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    private BoletimFurtoVeiculoService boletimFurtoVeiculoService;

    public CsvService(BoletimFurtoVeiculoService boletimFurtoVeiculoService) {
        this.boletimFurtoVeiculoService = boletimFurtoVeiculoService;
    }

    public List<BoletimFurtoVeiculo> lerArquivo() throws IOException {
            ClassPathResource recurso = new ClassPathResource("furtos.csv");

            try (BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(recurso.getInputStream(), StandardCharsets.UTF_8))) {

                String linha;
                int numeroLinha = 0;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                List<BoletimFurtoVeiculo> boletins = new ArrayList<>();
                while ((linha = leitor.readLine()) != null) {
                    numeroLinha++;

                    // pula cabeçalho se necessário
                    if (numeroLinha == 1 && linha.contains("ANO_BO")) {
                        continue;
                    }

                    String[] campos = linha.split("\t"); // tabulação como delimitador

                    // valida número de colunas antes de acessar índices
                    if (campos.length < 52) {
                        System.err.println("Linha " + numeroLinha + " ignorada (campos insuficientes)");
                        continue;
                    }

                    try {
                        // dados da ocorrencia
                        String data_ocorrencia = campos[5].trim();
                        String periodo_ocorrencia = campos[7].trim();

                        // endereco
                        Endereco endereco = new Endereco();
                        endereco.setLogradouro(campos[13].trim());
                        endereco.setNumero(campos[14].trim());
                        endereco.setBairro(campos[15].trim());
                        endereco.setCidade(campos[16].trim());
                        endereco.setEstado(campos[17].trim());

                        // veiculo
                        Veiculo veiculo = new Veiculo();
                        String ano_fabricacao = campos[49].trim();
                        String cor = campos[47].trim();
                        String marca = campos[48].trim();
                        String tipo_veiculo = campos[51].trim();

                        if (!ano_fabricacao.isEmpty()) {
                            veiculo.setAnoFabricacao(Integer.parseInt(ano_fabricacao));
                        }
                        veiculo.setCor(cor);
                        veiculo.setMarca(marca);
                        veiculo.setTipoVeiculo(tipo_veiculo);

                        // emplacamento
                        Emplacamento emplacamento = new Emplacamento();
                        emplacamento.setPlaca(campos[44].trim());
                        emplacamento.setEstado(campos[45].trim());
                        emplacamento.setCidade(campos[46].trim()); // conferido: cidade do veículo é depois da UF

                        veiculo.setEmplacamento(emplacamento);

                        // boletim
                        BoletimFurtoVeiculo boletim = new BoletimFurtoVeiculo();

                        if (!data_ocorrencia.isEmpty()) {
                            try {
                                LocalDate data = LocalDate.parse(data_ocorrencia, formatter);
                                boletim.setDataOcorrencia(Date.valueOf(data));
                            } catch (Exception e) {
                                System.err.println("Data inválida na linha " + numeroLinha + ": " + data_ocorrencia);
                            }
                        }

                        boletim.setPeriodoOcorrencia(periodo_ocorrencia);
                        boletim.setLocalOcorrencia(endereco);
                        boletim.setVeiculoFurtado(veiculo);

                        System.out.println("===============================================");
                        System.out.println(boletim);


                        boletins.add(boletim);

                        boletimFurtoVeiculoService.registrar(boletim);

                    } catch (Exception e) {
                        System.err.println("Erro ao processar linha " + numeroLinha + ": " + e.getMessage());
                    }
                }
                return boletins;

            }catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                throw e;
            }

        }


    }


