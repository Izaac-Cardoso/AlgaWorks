package com.izaac.api_exemplo.api_transito.controllers;

import com.izaac.api_exemplo.api_transito.models.Proprietario;
import com.izaac.api_exemplo.api_transito.models.exception.BusinessException;
import com.izaac.api_exemplo.api_transito.models.service.ProprietarioService;
import com.izaac.api_exemplo.api_transito.repository.ProprietarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{idProprietario}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long idProprietario) {
        return proprietarioRepository.findById(idProprietario)
                .map(proprietario -> ResponseEntity.ok(proprietario))
                .orElse(ResponseEntity.notFound().build());
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

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> BadRequestException(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
