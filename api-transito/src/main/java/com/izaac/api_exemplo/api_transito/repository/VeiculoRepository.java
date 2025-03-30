package com.izaac.api_exemplo.api_transito.repository;

import com.izaac.api_exemplo.api_transito.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
