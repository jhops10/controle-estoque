package com.jhops10.controle_estoque.domain.service;

import com.jhops10.controle_estoque.api.dto.StockMovementDTO;
import com.jhops10.controle_estoque.domain.model.MovementType;
import com.jhops10.controle_estoque.domain.model.Product;
import com.jhops10.controle_estoque.domain.model.StockMovement;
import com.jhops10.controle_estoque.domain.model.StockNotification;
import com.jhops10.controle_estoque.domain.repository.ProductRepository;
import com.jhops10.controle_estoque.domain.repository.StockMovementRepository;
import com.jhops10.controle_estoque.domain.repository.StockNotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;
    private final StockNotificationRepository stockNotificationRepository;
    private final EmailService emailService;

    public StockMovementService(StockMovementRepository stockMovementRepository, ProductRepository productRepository, StockNotificationRepository stockNotificationRepository, EmailService emailService) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
        this.stockNotificationRepository = stockNotificationRepository;
        this.emailService = emailService;
    }

    @Transactional
    public StockMovement registerMovement(StockMovementDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Produto com o id " + dto.getProductId() + " não encontrado"));


        if (dto.getType() == MovementType.OUTGOING && product.getQuantity() < dto.getQuantity()) {
            throw new IllegalArgumentException("Quantidade insuficiente no estoque para saída.");
        }

        if (dto.getType() == MovementType.INCOMING) {
            product.setQuantity(product.getQuantity() + dto.getQuantity());
        } else {
            product.setQuantity(product.getQuantity() - dto.getQuantity());
        }

        productRepository.save(product);

        StockMovement stockMovement = new StockMovement();
        stockMovement.setProduct(product);
        stockMovement.setType(dto.getType());
        stockMovement.setQuantity(dto.getQuantity());
        stockMovement.setUnitPrice(dto.getUnitValue());
        stockMovement.setDate(LocalDateTime.now());


        StockMovement savedMovement = stockMovementRepository.save(stockMovement);

        if (product.getMinimumStock() != null && product.getQuantity() < product.getMinimumStock()) {
            String message = String.format("Alerta: Produto '%s' abaixo do estoque mínimo (%d unidades)", product.getName(), product.getQuantity());

            StockNotification notification = new StockNotification();
            notification.setProduct(product);
            notification.setMessage(message);
            notification.setDate(LocalDateTime.now());


            emailService.sendEmail(
                    "email@email.com",
                    "⚠️ Estoque Baixo - " + product.getName(),
                    "O produto '" + product.getName() + "' está com estoque abaixo do mínimo. \n" +
                            "Estoque atual: " + product.getQuantity() + "\nEstoque mínimo: " + product.getMinimumStock()
            );
            stockNotificationRepository.save(notification);
        }

        return savedMovement;

    }
}
