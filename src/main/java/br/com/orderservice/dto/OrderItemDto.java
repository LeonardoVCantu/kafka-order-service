package br.com.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record OrderItemDto(
        @NotNull(message = "O ID do produto é obrigatório")
        UUID productId,

        @NotNull(message = "A quantidade deve ser preenchida")
        @Positive(message = "A quantidade deve ser maior que zero")
        Integer quantity
) {}