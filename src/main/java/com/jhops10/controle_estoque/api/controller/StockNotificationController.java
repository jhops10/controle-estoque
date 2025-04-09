package com.jhops10.controle_estoque.api.controller;

import com.jhops10.controle_estoque.api.dto.StockNotificationDTO;
import com.jhops10.controle_estoque.domain.service.StockNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class StockNotificationController {

    private final StockNotificationService stockNotificationService;

    public StockNotificationController(StockNotificationService stockNotificationService) {
        this.stockNotificationService = stockNotificationService;
    }

    @GetMapping
    public ResponseEntity<List<StockNotificationDTO>> getAllNotifications() {
        return ResponseEntity.ok(stockNotificationService.listAll());
    }
}
