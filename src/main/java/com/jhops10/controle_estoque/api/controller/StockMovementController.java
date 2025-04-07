package com.jhops10.controle_estoque.api.controller;

import com.jhops10.controle_estoque.api.dto.StockMovementDTO;
import com.jhops10.controle_estoque.domain.model.StockMovement;
import com.jhops10.controle_estoque.domain.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping
    public ResponseEntity<StockMovement> registerMovement(@RequestBody @Valid StockMovementDTO dto) {
        StockMovement movement = stockMovementService.registerMovement(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movement);
    }
}
