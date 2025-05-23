package com.izaac.api_exemplo.api_transito.domain.repository;

import com.izaac.api_exemplo.api_transito.domain.models.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    Optional<Proprietario> findByEmail(String email);
    Optional<Proprietario> findById(Long id);
    List<Proprietario> findBynome(String nome);
}
