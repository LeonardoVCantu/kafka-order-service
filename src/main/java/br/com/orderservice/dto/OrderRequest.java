package br.com.orderservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderRequest(
        @NotNull(message = "O ID do cliente é obrigatório")
        UUID clientId,

        @NotNull(message = "O valor total deve ser preenchido")
        @Positive(message = "O valor total deve ser maior que zero")
        BigDecimal totalValue,

        @NotEmpty(message = "O pedido deve conter pelo menos um item")
        List<OrderItemDto> items
) {}
