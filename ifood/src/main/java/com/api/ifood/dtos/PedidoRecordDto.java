package com.api.ifood.dtos;

import com.api.ifood.models.Client;
import com.api.ifood.models.Establishment;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PedidoRecordDto(@NotNull UUID client_id, @NotNull UUID establishment_id, @NotNull Double preco) {

}
