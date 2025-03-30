package com.izaac.api_exemplo.api_transito.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String marca;
    private String modelo;
    private String placa;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataApreensao;


    public Veiculo(String marca, String modelo, String placa, LocalDateTime dataCadastro, LocalDateTime dataApreensao) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.dataCadastro = dataCadastro;
        this.dataApreensao = dataApreensao;
    }
}
