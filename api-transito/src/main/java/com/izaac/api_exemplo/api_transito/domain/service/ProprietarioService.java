package com.izaac.api_exemplo.api_transito.domain.service;

import com.izaac.api_exemplo.api_transito.domain.models.Proprietario;
import com.izaac.api_exemplo.api_transito.domain.exceptions.BusinessException;
import com.izaac.api_exemplo.api_transito.domain.repository.ProprietarioRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public List<Proprietario> listaCompleta() {
        return proprietarioRepository.findAll();
    }

    public Optional<Proprietario> buscarPorId(Long idProprietario) {
        return proprietarioRepository.findById(idProprietario);
    }

    @Transactional
    private boolean validaEmail(Proprietario proprietario) {
        boolean emailEmUso;
        return emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();
    }

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        if(validaEmail(proprietario)) {
            throw new BusinessException("Já existe um usuário com esse e-mail cadastrado.");
        }
        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public ResponseEntity<Proprietario> atualizar(Long idProprietario, Proprietario proprietario) {
        if(!proprietarioRepository.existsById(idProprietario)) {
            return ResponseEntity.notFound().build();
        }

        var proprietarioAtualizado = salvar(proprietario);
        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @Transactional
    public ResponseEntity<Void> deletar(Long idProprietario) {
        if(!proprietarioRepository.existsById(idProprietario)) {
            return ResponseEntity.notFound().build();
        }
        proprietarioRepository.deleteById(idProprietario);
        return ResponseEntity.noContent().build();
    }

}
