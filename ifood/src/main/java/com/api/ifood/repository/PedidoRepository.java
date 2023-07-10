package com.api.ifood.repository;

import com.api.ifood.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository <Pedido, UUID> {
}
