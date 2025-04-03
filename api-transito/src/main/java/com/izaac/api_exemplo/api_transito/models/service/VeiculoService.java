package com.izaac.api_exemplo.api_transito.models.service;

import com.izaac.api_exemplo.api_transito.models.Veiculo;
import com.izaac.api_exemplo.api_transito.repository.VeiculoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    private final VeiculoRepositorio veiculoRepositorio;

    public VeiculoService(VeiculoRepositorio veiculoRepositorio) {
        this.veiculoRepositorio = veiculoRepositorio;
    }

    public ResponseEntity<Veiculo> atualizarVeiculo(Long idVeiculo, Veiculo veiculo) {
        if(!veiculoRepositorio.existsById(idVeiculo)) {
            return ResponseEntity.notFound().build();
        }

        var veiculoAtulizado = veiculoRepositorio.save(veiculo);
        return ResponseEntity.ok(veiculoAtulizado);
    }

    public ResponseEntity<Void> delete(Long idVeiculo) {
        if(!veiculoRepositorio.existsById(idVeiculo)) {
            return ResponseEntity.notFound().build();
        }
        veiculoRepositorio.deleteById(idVeiculo);
        return ResponseEntity.noContent().build();
    }
}
