package com.izaac.api_exemplo.api_transito.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.izaac.api_exemplo.api_transito.domain.validation.ValidationProprietarioId;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;

import java.time.LocalDateTime;

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
    private LocalDateTime dataCadastro;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataApreensao;

    public Veiculo() {
    }

    public Veiculo(long idVeiculo, Proprietario proprietario, StatusVeiculo statusVeiculo, String marca, String modelo, String placa, LocalDateTime dataCadastro, LocalDateTime dataApreensao) {
        this.idVeiculo = idVeiculo;
        this.proprietario = proprietario;
        this.statusVeiculo = statusVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.dataCadastro = dataCadastro;
        this.dataApreensao = dataApreensao;
    }

    public void setId(long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public void setDataApreensao(LocalDateTime dataApreensao) {
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public LocalDateTime getDataApreensao() {
        return dataApreensao;
    }

}
