package com.izaac.api_exemplo.api_transito.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Proprietario proprietario;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo statusVeiculo;

    private String marca;
    private String modelo;
    private String placa;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataApreensao;

    public Veiculo() {
    }

    public Veiculo(long id, Proprietario proprietario, StatusVeiculo statusVeiculo, String marca, String modelo, String placa, LocalDateTime dataCadastro, LocalDateTime dataApreensao) {
        this.id = id;
        this.proprietario = proprietario;
        this.statusVeiculo = statusVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.dataCadastro = dataCadastro;
        this.dataApreensao = dataApreensao;
    }

    public void setId(long id) {
        this.id = id;
    }
}
