package com.izaac.api_exemplo.api_transito.domain.service;

import com.izaac.api_exemplo.api_transito.domain.models.Proprietario;
import com.izaac.api_exemplo.api_transito.domain.models.StatusVeiculo;
import com.izaac.api_exemplo.api_transito.domain.models.Veiculo;
import com.izaac.api_exemplo.api_transito.dto.VeiculoDTO;
import com.izaac.api_exemplo.api_transito.exceptionHandler.BusinessException;
import com.izaac.api_exemplo.api_transito.domain.repository.ProprietarioRepository;
import com.izaac.api_exemplo.api_transito.domain.repository.VeiculoRepositorio;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@Service
public class VeiculoService {

    private final VeiculoRepositorio veiculoRepositorio;
    private final ProprietarioRepository proprietarioRepository;

    public VeiculoService(VeiculoRepositorio veiculoRepositorio, ProprietarioRepository proprietarioRepository) {
        this.veiculoRepositorio = veiculoRepositorio;
        this.proprietarioRepository = proprietarioRepository;
    }

    private boolean validaPlaca(Veiculo veiculo) {
        boolean placaEmUso;
        return placaEmUso = veiculoRepositorio.findByPlaca(veiculo.getPlaca())
                .filter(v -> !v.equals(veiculo))
                .isPresent();
    }

    @Transactional
    public Veiculo cadastrar(Veiculo veiculo) {

        Proprietario proprietario = proprietarioRepository.findById(veiculo.getProprietario().getId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado."));

        veiculo.setProprietario(proprietario);
        veiculo.setStatusVeiculo(StatusVeiculo.REGULAR);
        veiculo.setDataCadastro(OffsetDateTime.now());

        if(validaPlaca(veiculo)) {
            throw new BusinessException("Já existe um veículo cadastrado com essa placa.");
        }
        return veiculoRepositorio.save(veiculo);
    }

    @Transactional
    public ResponseEntity<Veiculo> atualizarVeiculo(Long idVeiculo, Veiculo veiculo) {
        if(!veiculoRepositorio.existsById(idVeiculo)) {
            return ResponseEntity.notFound().build();
        }

        var veiculoAtulizado = veiculoRepositorio.save(veiculo);
        return ResponseEntity.ok(veiculoAtulizado);
    }

    @Transactional
    public ResponseEntity<Void> delete(Long idVeiculo) {
        if(!veiculoRepositorio.existsById(idVeiculo)) {
            return ResponseEntity.notFound().build();
        }
        veiculoRepositorio.deleteById(idVeiculo);
        return ResponseEntity.noContent().build();
    }
}
