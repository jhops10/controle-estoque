package com.jhops10.controle_estoque.domain.service;

import com.jhops10.controle_estoque.api.dto.StockNotificationDTO;
import com.jhops10.controle_estoque.domain.repository.StockNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockNotificationService {

    private final StockNotificationRepository stockNotificationRepository;

    public StockNotificationService(StockNotificationRepository stockNotificationRepository) {
        this.stockNotificationRepository = stockNotificationRepository;
    }

    public List<StockNotificationDTO> listAll() {
        return stockNotificationRepository.findAll()
                .stream()
                .map(notification -> new StockNotificationDTO(
                        notification.getId(),
                        notification.getProduct().getName(),
                        notification.getMessage(),
                        notification.getDate()
                )).toList();
    }
}
