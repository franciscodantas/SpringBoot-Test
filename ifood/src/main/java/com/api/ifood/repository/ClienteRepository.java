package com.api.ifood.repository;

import com.api.ifood.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository <Client, UUID>{
    boolean existsByNome(String nome);
}
