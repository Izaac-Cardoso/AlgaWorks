package com.izaac.api_exemplo.api_transito.domain.service;

import com.izaac.api_exemplo.api_transito.domain.models.Proprietario;
import com.izaac.api_exemplo.api_transito.domain.models.StatusVeiculo;
import com.izaac.api_exemplo.api_transito.domain.models.Veiculo;
import com.izaac.api_exemplo.api_transito.dto.VeiculoDTO;
import com.izaac.api_exemplo.api_transito.domain.exceptions.BusinessException;
import com.izaac.api_exemplo.api_transito.domain.repository.ProprietarioRepository;
import com.izaac.api_exemplo.api_transito.domain.repository.VeiculoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    private final VeiculoRepositorio veiculoRepositorio;
    private final ProprietarioRepository proprietarioRepository;

    public VeiculoService(VeiculoRepositorio veiculoRepositorio, ProprietarioRepository proprietarioRepository) {
        this.veiculoRepositorio = veiculoRepositorio;
        this.proprietarioRepository = proprietarioRepository;
    }

    public List<VeiculoDTO> listaCompleta() {

        return veiculoRepositorio.findAll()
                .stream()
                .map(VeiculoDTO::ofEntity)
                .collect(Collectors.toList());
    }

    public VeiculoDTO buscarPorId(Long idVeiculo) {
        Veiculo veiculo = veiculoRepositorio.findById(idVeiculo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Veiculo não encontrado")).getBody();

        return  VeiculoDTO.ofEntity(veiculo);
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
    public VeiculoDTO atualizarVeiculo(Long idVeiculo, Veiculo veiculo) {
        if(!veiculoRepositorio.existsById(idVeiculo)) {
        //    return ResponseEntity.notFound().build();
            throw new BusinessException("Veículo não encontrado na base de dados");
        }

        var veiculoAtulizado = veiculoRepositorio.save(veiculo);
        return VeiculoDTO.ofEntity(veiculoAtulizado);
    //    return ResponseEntity.ok(veiculoAtulizado);
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
