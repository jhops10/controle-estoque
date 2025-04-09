package com.jhops10.controle_estoque.api.dto;

import java.time.LocalDateTime;

public record StockNotificationDTO(
        Long id,
        String productName,
        String message,
        LocalDateTime date
) {
}
