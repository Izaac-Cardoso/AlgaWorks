package com.izaac.api_exemplo.api_transito.controllers;

import com.izaac.api_exemplo.api_transito.domain.models.Proprietario;
import com.izaac.api_exemplo.api_transito.domain.service.ProprietarioService;
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

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @GetMapping
    public List<ProprietarioDTO> listar() {
        return proprietarioService.listaCompleta().stream()
                                .map(ProprietarioDTO::ofEntity)
                                .collect(Collectors.toList());
    }

    @GetMapping("/{idProprietario}")
    public ResponseEntity<ProprietarioDTO> buscar(@PathVariable Long idProprietario) {
        Proprietario proprietario = proprietarioService.buscarPorId(idProprietario)
                                    .map(ResponseEntity::ok)
                                    .orElse(ResponseEntity.notFound().build()).getBody();

        var proprietarioDTO = ProprietarioDTO.ofEntity(proprietario);
        return ResponseEntity.ok(proprietarioDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProprietarioDTO inserir(@Valid @RequestBody Proprietario proprietario) {
        return ProprietarioDTO.ofEntity(proprietarioService.salvar(proprietario));
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
