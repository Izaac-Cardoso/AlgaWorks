package com.izaac.api_exemplo.api_transito.controllers;

import com.izaac.api_exemplo.api_transito.domain.models.Veiculo;
import com.izaac.api_exemplo.api_transito.domain.exception.BusinessException;
import com.izaac.api_exemplo.api_transito.domain.service.VeiculoService;
import com.izaac.api_exemplo.api_transito.domain.repository.VeiculoRepositorio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoRepositorio veiculoRepositorio;
    private final VeiculoService veiculoService;


    public VeiculoController(VeiculoRepositorio veiculoRepositorio, VeiculoService veiculoService) {
        this.veiculoRepositorio = veiculoRepositorio;
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<Veiculo> listar() {
        return veiculoRepositorio.findAll();
    }

    @GetMapping("/{idVeiculo}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long idVeiculo) {
        return veiculoRepositorio.findById(idVeiculo)
                .map(veiculo -> ResponseEntity.ok(veiculo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) {
        return veiculoService.cadastrar(veiculo);
    }

    @PutMapping("/{idVeiculo}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long idVeiculo, @RequestBody Veiculo veiculo) {
        veiculo.setId(idVeiculo);
        return veiculoService.atualizarVeiculo(idVeiculo, veiculo);
    }

    @DeleteMapping("/{idVeiculo}")
    public ResponseEntity<Void> deletar(@PathVariable Long idVeiculo) {
        return veiculoService.delete(idVeiculo);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
