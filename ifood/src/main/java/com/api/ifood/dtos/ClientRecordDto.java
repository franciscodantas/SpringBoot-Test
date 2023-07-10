package com.api.ifood.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientRecordDto(@NotBlank String nome, @NotBlank String endereco) {
}
