package com.izaac.api_exemplo.api_transito.domain.service;

import com.izaac.api_exemplo.api_transito.domain.exceptions.BusinessException;
import com.izaac.api_exemplo.api_transito.domain.repository.VeiculoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApreensaoService {

    private final VeiculoRepositorio veiculoRepositorio;

    public ApreensaoService(VeiculoRepositorio veiculoRepositorio) {
        this.veiculoRepositorio = veiculoRepositorio;
    }

    @Transactional
    public void apreenderVeiculo(Long idVeiculo) {

        var veiculo = veiculoRepositorio.findById(idVeiculo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Veiculo não encontrado")).getBody();

        veiculo.apreender();
    }

    @Transactional
    public void removeApreensao(Long idVeiculo) {

        var veiculo = veiculoRepositorio.findById(idVeiculo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Veiculo não encontrado")).getBody();

        veiculo.removerApreensao();
    }
}
