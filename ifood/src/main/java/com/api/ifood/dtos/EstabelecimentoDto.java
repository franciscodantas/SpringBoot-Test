package com.api.ifood.dtos;

import jakarta.validation.constraints.NotBlank;

public record EstabelecimentoDto(@NotBlank String nome, @NotBlank String endereco) {
}
