package com.izaac.api_exemplo.api_transito.dto;

import com.izaac.api_exemplo.api_transito.domain.models.Proprietario;
import org.springframework.http.ResponseEntity;

public class ProprietarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

    private ProprietarioDTO(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.email = builder.email;
        this.telefone = builder.telefone;
    }

    public static Builder builder() {
       return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public static ProprietarioDTO ofEntity(Proprietario proprietario) {
        var proprietarioDTO = ProprietarioDTO.builder()
                                .id(proprietario.getId())
                                .nome(proprietario.getNome())
                                .email(proprietario.getEmail())
                                .telefone(proprietario.getTelefone()).build();

        return proprietarioDTO;
    }

    public static class Builder {

        private Long id;
        private String nome;
        private String email;
        private String telefone;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public ProprietarioDTO build() {
            return new ProprietarioDTO(this);
        }

    }

}
