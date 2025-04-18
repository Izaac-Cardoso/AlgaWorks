package com.izaac.api_exemplo.api_transito.domain.repository;

import com.izaac.api_exemplo.api_transito.domain.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {

    public Optional<Veiculo> findByPlaca(String s);
}
