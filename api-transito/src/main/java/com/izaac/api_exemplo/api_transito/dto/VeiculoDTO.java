package com.izaac.api_exemplo.api_transito.dto;

import com.izaac.api_exemplo.api_transito.domain.models.Autuacao;
import com.izaac.api_exemplo.api_transito.domain.models.StatusVeiculo;
import com.izaac.api_exemplo.api_transito.domain.models.Veiculo;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDTO {

    private Long id;
    private String proprietario;
    private StatusVeiculo statusVeiculo;
    private String marca;
    private String modelo;
    private String placa;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    private List<Autuacao> autuacoes = new ArrayList<>();

    private VeiculoDTO(Builder builder) {
        this.id = builder.id;
        this.proprietario = builder.proprietario;
        this.statusVeiculo = builder.statusVeiculo;
        this.marca = builder.marca;
        this.modelo = builder.modelo;
        this.placa = builder.placa;
        this.dataCadastro = builder.dataCadastro;
        this.dataApreensao = builder.dataApreensao;
        this.autuacoes = builder.autuacaoList;
    }

    public Long getId() {
        return id;
    }

    public String getProprietario() {
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

    public List<Autuacao> getAutuacoes() {return autuacoes;}

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public OffsetDateTime getDataApreensao() {
        return dataApreensao;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static VeiculoDTO ofEntity(Veiculo veiculo) {
        var veiculoDTO = VeiculoDTO.builder()
                            .id(veiculo.getIdVeiculo())
                            .proprietario(veiculo.getProprietario().getNome())
                            .statusVeiculo(veiculo.getStatusVeiculo())
                            .marca(veiculo.getMarca())
                            .modelo(veiculo.getModelo())
                            .placa(veiculo.getPlaca())
                            .dataCadastro(veiculo.getDataCadastro())
                            .dataApreensao(veiculo.getDataApreensao())
                            .autuacoes(veiculo.getAutuacoes())
                            .build();

        return veiculoDTO;
    }

    public static class Builder {

        private Long id;
        private String proprietario;
        private StatusVeiculo statusVeiculo;
        private String marca;
        private String modelo;
        private String placa;
        private OffsetDateTime dataCadastro;
        private OffsetDateTime dataApreensao;

        private List<Autuacao> autuacaoList = new ArrayList<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder proprietario(String proprietario) {
            this.proprietario = proprietario;
            return this;
        }

        public Builder statusVeiculo(StatusVeiculo statusVeiculo) {
            this.statusVeiculo = statusVeiculo;
            return this;
        }

        public Builder marca(String marca) {
            this.marca = marca;
            return this;
        }

        public Builder modelo(String modelo) {
            this.modelo = modelo;
            return this;
        }

        public Builder placa(String placa) {
            this.placa = placa;
            return this;
        }

        public Builder dataCadastro(OffsetDateTime dataCadastro) {
            this.dataCadastro = dataCadastro;
            return this;
        }

        public Builder dataApreensao(OffsetDateTime dataApreensao) {
            this.dataApreensao = dataApreensao;
            return this;
        }

        public Builder autuacoes(List<Autuacao> autuacoes) {
            this.autuacaoList = autuacoes;
            return this;
        }

        public VeiculoDTO build() {
            return new VeiculoDTO(this);
        }

    }

}
