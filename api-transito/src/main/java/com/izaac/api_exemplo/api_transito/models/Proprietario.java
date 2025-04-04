package com.izaac.api_exemplo.api_transito.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Proprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProprietario;

    @NotBlank
    @Size(max=60)
    private String nome;

    @NotBlank
    @Size(max=200)
    @Email
    private String email;

    @NotBlank
    @Size(max=20)
    private String telefone;

    //private Veiculo veiculo;

    public Proprietario() {
    }

    public Proprietario(Long idProprietario, String nome, String email, String telefone) {
        this.idProprietario = idProprietario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public void setId(Long idProprietario) {
        this.idProprietario = idProprietario;
    }

    public Long getId() {
        return idProprietario;
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
}
