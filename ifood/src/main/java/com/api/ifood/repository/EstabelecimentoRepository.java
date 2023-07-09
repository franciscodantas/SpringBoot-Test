package com.api.ifood.repository;

import com.api.ifood.models.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Establishment, UUID> {
    boolean existsByNome(String nome);
}
