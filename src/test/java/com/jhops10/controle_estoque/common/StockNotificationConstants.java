package com.jhops10.controle_estoque.common;

import com.jhops10.controle_estoque.api.dto.StockNotificationDTO;
import com.jhops10.controle_estoque.domain.model.StockNotification;

import java.time.LocalDateTime;
import java.util.List;

public class StockNotificationConstants {

    public static final StockNotificationDTO STOCK_NOTIFICATION_DTO = new StockNotificationDTO(
            1L,
            "Nome Produto",
            "Mensagem Notoficação",
            LocalDateTime.now());

    public static final StockNotification STOCK_NOTIFICATION = new StockNotification(
            STOCK_NOTIFICATION_DTO.id(),
            ProductConstants.PRODUCT,
            STOCK_NOTIFICATION_DTO.message(),
            STOCK_NOTIFICATION_DTO.date()
    );

    public static final List<StockNotification> STOCK_NOTIFICATION_LIST = List.of(STOCK_NOTIFICATION);

}
