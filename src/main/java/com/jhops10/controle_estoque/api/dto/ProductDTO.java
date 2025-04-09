package com.jhops10.controle_estoque.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDTO(
        @NotBlank(message = "O nome do produto é obrigatório.")
        String name,

        @NotBlank(message = "A descrição do produto é obrigatória.")
        String description,

        @NotNull(message = "A quantidade em estoque é obrigatória.")
        @Min(value = 0, message = "A quantidade não pode ser negativa.")
        Integer quantity,

        @NotNull(message = "O preço é um campo obrigatório.")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero.")
        BigDecimal price,

        @NotNull(message = "O Id do fornecedor é um campo obrigatório.")
        Long supplierId,

        @Min(0)
        Integer minimumStock
) {
}
