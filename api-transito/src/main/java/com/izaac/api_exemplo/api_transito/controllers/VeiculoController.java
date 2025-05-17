package com.izaac.api_exemplo.api_transito.controllers;

import com.izaac.api_exemplo.api_transito.domain.models.Veiculo;
import com.izaac.api_exemplo.api_transito.domain.service.VeiculoService;
import com.izaac.api_exemplo.api_transito.dto.VeiculoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> listar() {
        List<VeiculoDTO> veiculosDTO = veiculoService.listaCompleta();
        return ResponseEntity.ok(veiculosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscar(@PathVariable Long id) {
        VeiculoDTO veiculoDTO = veiculoService.buscarPorId(id);
        return ResponseEntity.ok(veiculoDTO);
    }


//    @GetMapping("/{idVeiculo}")
//    public ResponseEntity<VeiculoDTO> buscar(@PathVariable Long idVeiculo) {
//        Veiculo veiculo = veiculoService.buscarPorId(idVeiculo)
//                          .map(ResponseEntity::ok)
//                          .orElse(ResponseEntity.notFound().build()).getBody();
//
//        var veiculoDTO = VeiculoDTO.ofEntity(veiculo);
//        return ResponseEntity.ok(veiculoDTO);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoDTO cadastrar(@Valid @RequestBody Veiculo veiculo) {
        return VeiculoDTO.ofEntity(veiculoService.cadastrar(veiculo));
    }

    @PutMapping("/{idVeiculo}")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long idVeiculo, @RequestBody Veiculo veiculo) {
        veiculo.setId(idVeiculo);
        var veiculoAtualizado = veiculoService.atualizarVeiculo(idVeiculo, veiculo);
        return ResponseEntity.ok(veiculoAtualizado);
    }

    @DeleteMapping("/{idVeiculo}")
    public ResponseEntity<Void> deletar(@PathVariable Long idVeiculo) {
        return veiculoService.delete(idVeiculo);
    }
}
