package com.jhops10.controle_estoque.common;

import com.jhops10.controle_estoque.api.dto.StockMovementDTO;
import com.jhops10.controle_estoque.domain.model.MovementType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockMovementConstants {

    public static final StockMovementDTO STOCK_MOVEMENT_DTO = new StockMovementDTO(1L, MovementType.INCOMING, 5, new BigDecimal("10.00"));
    public static final StockMovementDTO STOCK_MOVEMENT_DTO_OUTGOING = new StockMovementDTO(1L, MovementType.OUTGOING, 5, new BigDecimal("9.00"));
}
