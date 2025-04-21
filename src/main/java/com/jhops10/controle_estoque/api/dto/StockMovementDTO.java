package com.jhops10.controle_estoque.api.dto;

import com.jhops10.controle_estoque.domain.model.MovementType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class StockMovementDTO {

    @NotNull(message = "O ID do produto é obrigatório.")
    private Long productId;

    @NotNull(message = "O tipo de movimentação é obrigatório.")
    private MovementType type;

    @Min(value = 1, message = "A quantidade deve zer maior que zero.")
    private Integer quantity;

    @NotNull(message = "O valor unitário é obrigatório.")
    private BigDecimal unitValue;

    public StockMovementDTO() {
    }

    public StockMovementDTO(Long productId, MovementType type, Integer quantity, BigDecimal unitValue) {
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.unitValue = unitValue;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }
}
