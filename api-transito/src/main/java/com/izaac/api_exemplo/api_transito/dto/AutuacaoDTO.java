package com.izaac.api_exemplo.api_transito.dto;

import com.izaac.api_exemplo.api_transito.domain.models.Autuacao;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class AutuacaoDTO {

    private Long id;
    private String veiculo;
    private BigDecimal valorMulta;
    private OffsetDateTime dataOcorrencia;

    private AutuacaoDTO(Builder builder) {
        this.id = builder.id;
        this.veiculo = builder.descricao;
        this.valorMulta = builder.valorMulta;
        this.dataOcorrencia = builder.dataOcorrencia;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public OffsetDateTime getDataOcorrencia() {
        return dataOcorrencia;
    }

    public static AutuacaoDTO ofEntity(Autuacao autuacao) {
        var autuacaoDTO = AutuacaoDTO.builder()
                .id(autuacao.getId())
                .descricao(autuacao.getDescricao())
                .dataOcorrencia(autuacao.getDataOcorrencia())
                .valorMulta(autuacao.getValorMulta())
                .build();

        return autuacaoDTO;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String descricao;
        private BigDecimal valorMulta;
        private OffsetDateTime dataOcorrencia;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder valorMulta(BigDecimal valorMulta) {
            this.valorMulta = valorMulta;
            return this;
        }

        public Builder dataOcorrencia(OffsetDateTime dataOcorrencia) {
            this.dataOcorrencia = dataOcorrencia;
            return this;
        }

        public AutuacaoDTO build() {
            return new AutuacaoDTO(this);
        }
    }
}