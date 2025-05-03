package com.izaac.api_exemplo.api_transito.controllers;

import com.izaac.api_exemplo.api_transito.domain.models.Proprietario;
import com.izaac.api_exemplo.api_transito.domain.service.ProprietarioService;
import com.izaac.api_exemplo.api_transito.domain.repository.ProprietarioRepository;
import com.izaac.api_exemplo.api_transito.dto.ProprietarioDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioRepository proprietarioRepository, ProprietarioService proprietarioService) {
        this.proprietarioRepository = proprietarioRepository;
        this.proprietarioService = proprietarioService;
    }

    @GetMapping
    public List<ResponseEntity<ProprietarioDTO>> listar() {
        return proprietarioRepository.findAll().stream()
                                .map(ProprietarioDTO::ofEntity)
                                .collect(Collectors.toList());
    }

    @GetMapping("/{idProprietario}")
    public ResponseEntity<ProprietarioDTO> buscar(@PathVariable Long idProprietario) {
        Proprietario proprietario = proprietarioRepository.findById(idProprietario)
                                    .map(ResponseEntity::ok)
                                    .orElse(ResponseEntity.notFound().build()).getBody();

        return ProprietarioDTO.ofEntity(proprietario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario inserir(@Valid @RequestBody Proprietario proprietario) {
        return proprietarioService.salvar(proprietario);
    }

    @PutMapping("/{idProprietario}")
    public ResponseEntity<Proprietario> atualizar(@Valid @PathVariable Long idProprietario, @Valid @RequestBody Proprietario proprietario) {

        proprietario.setId(idProprietario);
        var proprietarioAtualizado = proprietarioService.atualizar(idProprietario, proprietario);
        return ResponseEntity.ok(proprietarioAtualizado.getBody());
    }

    @DeleteMapping("/{idProprietario}")
    public ResponseEntity<Void> deletar(@PathVariable Long idProprietario) {
        return proprietarioService.deletar(idProprietario);
    }
}
