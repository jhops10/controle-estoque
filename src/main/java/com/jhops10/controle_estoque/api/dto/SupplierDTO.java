package com.jhops10.controle_estoque.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SupplierDTO(
        @NotBlank String name,
        @Email String email) {
}
