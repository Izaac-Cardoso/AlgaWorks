package com.izaac.api_exemplo.api_transito.domain.models;

import com.izaac.api_exemplo.api_transito.domain.validation.ValidationProprietarioId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Validation;
import jakarta.validation.constraints.*;

@Entity
public class Proprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationProprietarioId.class)
    private Long idProprietario;

    @NotBlank//(message = "Informe um nome de proprietário")
    @Size(max=60)
    private String nome;

    @NotBlank//(message = "O e-mail deve ser bem formatado")
    @Size(max=200)
    @Email
    private String email;

    @NotBlank(message = "Informe um número de contato")
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
