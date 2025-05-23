package com.izaac.api_exemplo.api_transito.domain.service;

import com.izaac.api_exemplo.api_transito.domain.exceptions.RecursoNaoEncontradoException;
import com.izaac.api_exemplo.api_transito.domain.models.Autuacao;
import com.izaac.api_exemplo.api_transito.domain.repository.VeiculoRepositorio;
import com.izaac.api_exemplo.api_transito.domain.exceptions.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutuacaoService {

    private final VeiculoRepositorio veiculoRepositorio;

    public AutuacaoService(VeiculoRepositorio veiculoRepositorio) {
        this.veiculoRepositorio = veiculoRepositorio;
    }

    @Transactional
    public Autuacao registrarAutuacao(Long idVeiculo, Autuacao autuacao) {

       var veiculo = veiculoRepositorio.findById(idVeiculo)
               .orElseThrow(() -> new RecursoNaoEncontradoException("Recurso não encontrado."));

       return veiculo.cadastraAutuacao(autuacao);
    }
}
