package com.izaac.api_exemplo.api_transito.controllers;

import com.izaac.api_exemplo.api_transito.domain.models.Autuacao;
import com.izaac.api_exemplo.api_transito.domain.service.AutuacaoService;
import com.izaac.api_exemplo.api_transito.domain.service.VeiculoService;
import com.izaac.api_exemplo.api_transito.dto.AutuacaoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("veiculo/{idVeiculo}/autuacoes")
public class AutuacoeController {

    private final AutuacaoService autuacaoService;
    private final VeiculoService veiculoService;

    public AutuacoeController(AutuacaoService autuacaoService, VeiculoService veiculoService) {
        this.autuacaoService = autuacaoService;
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<AutuacaoDTO> listarAutuacoes(@PathVariable Long idVeiculo) {
        var veiculo = veiculoService.buscarPorId(idVeiculo);
        return  veiculo.getAutuacoes().stream()
                           .map(AutuacaoDTO::ofEntity)
                           .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoDTO cadastrarAutuacao(@PathVariable Long idVeiculo, @Valid @RequestBody Autuacao autuacao) {
        var notificacao = autuacaoService.registrarAutuacao(idVeiculo, autuacao);
        return AutuacaoDTO.ofEntity(notificacao);
    }
}

