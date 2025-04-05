package com.izaac.api_exemplo.api_transito.models.service;

import com.izaac.api_exemplo.api_transito.models.Veiculo;
import com.izaac.api_exemplo.api_transito.models.exception.BusinessException;
import com.izaac.api_exemplo.api_transito.repository.VeiculoRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    private final VeiculoRepositorio veiculoRepositorio;

    public VeiculoService(VeiculoRepositorio veiculoRepositorio) {
        this.veiculoRepositorio = veiculoRepositorio;
    }

    public Veiculo inserir(Veiculo veiculo) {
        if(veiculoRepositorio.existsById(veiculo.getIdVeiculo())) {
            throw new BusinessException("Já existe um veículo cadastrado com essa placa.");
        }
        return veiculoRepositorio.save(veiculo);
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
