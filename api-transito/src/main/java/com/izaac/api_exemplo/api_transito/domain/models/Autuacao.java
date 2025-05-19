package com.izaac.api_exemplo.api_transito.domain.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Autuacao {

    @Id
    @Column(name = "id_autuacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    private String descricao;
    private BigDecimal valorMulta;
    private OffsetDateTime dataOcorrencia;

    public Autuacao() {}

    public Autuacao(Long id, Veiculo veiculo, String descricao, BigDecimal valorMulta, OffsetDateTime dataOcorrencia) {
        this.id = id;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.valorMulta = valorMulta;
        this.dataOcorrencia = dataOcorrencia;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataOcorrencia(OffsetDateTime dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public OffsetDateTime getDataOcorrencia() {
        return dataOcorrencia;
    }

}
