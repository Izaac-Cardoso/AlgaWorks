package com.izaac.api_exemplo.api_transito.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.izaac.api_exemplo.api_transito.domain.exceptions.BusinessException;
import com.izaac.api_exemplo.api_transito.domain.validation.ValidationProprietarioId;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVeiculo;

    @Valid
    @ManyToOne
    @JoinColumn(name = "id_proprietario")
    @ConvertGroup(from = Default.class, to = ValidationProprietarioId.class)
    private Proprietario proprietario;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo statusVeiculo;

    @NotBlank(message = "informe a marca")
    private String marca;
    @NotBlank(message = "informe o modelo")
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataCadastro;

    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Autuacao> autuacaos = new ArrayList<>();

    public Veiculo() {
    }

    public Veiculo(long idVeiculo, Proprietario proprietario, StatusVeiculo statusVeiculo, String marca, String modelo, String placa, OffsetDateTime dataCadastro, OffsetDateTime dataApreensao) {
        this.idVeiculo = idVeiculo;
        this.proprietario = proprietario;
        this.statusVeiculo = statusVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.dataCadastro = dataCadastro;
        this.dataApreensao = dataApreensao;
    }

    public Autuacao cadastraAutuacao(Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        autuacaos.add(autuacao);
        return autuacao;
    }

    private boolean statusDoVeiculo() {
        return StatusVeiculo.APREENDIDO.equals(getStatusVeiculo());
    }

    public void apreender() {
        if(statusDoVeiculo()) {
            throw new BusinessException("Esse veículo já está apreendido!");
        }

        this.setStatusVeiculo(StatusVeiculo.APREENDIDO);
        this.setDataApreensao(OffsetDateTime.now());
    }

    public void removerApreensao() {
        if(!statusDoVeiculo()) {
            throw new BusinessException("Esse veículo não está apreendido");
        }

        this.setStatusVeiculo(StatusVeiculo.REGULAR);
        this.setDataApreensao(null);
    }

    public void setId(long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public void setDataApreensao(OffsetDateTime dataApreensao) {
        this.dataApreensao = dataApreensao;
    }

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public StatusVeiculo getStatusVeiculo() {
        return statusVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public OffsetDateTime getDataApreensao() {
        return dataApreensao;
    }

    public List<Autuacao> getAutuacoes() {
        return autuacaos;
    }
}
