package com.izaac.api_exemplo.api_transito.controllers;

import com.izaac.api_exemplo.api_transito.domain.models.Veiculo;
import com.izaac.api_exemplo.api_transito.domain.service.VeiculoService;
import com.izaac.api_exemplo.api_transito.domain.repository.VeiculoRepositorio;
import com.izaac.api_exemplo.api_transito.dto.VeiculoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Long idVeiculo) {
        Veiculo veiculo = veiculoRepositorio.findById(idVeiculo)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build()).getBody();

        return VeiculoDTO.ofEntity(veiculo);
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
}
