package com.izaac.api_exemplo.api_transito.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Autuacao {

    private String descricao;
    private BigDecimal valorMulta;
    private LocalDateTime dataOcorrencia;

    public Autuacao() {
    }

    public Autuacao(String descricao, BigDecimal valorMulta, LocalDateTime dataOcorrencia) {
        this.descricao = descricao;
        this.valorMulta = valorMulta;
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public LocalDateTime getDataOcorrencia() {
        return dataOcorrencia;
    }
}
