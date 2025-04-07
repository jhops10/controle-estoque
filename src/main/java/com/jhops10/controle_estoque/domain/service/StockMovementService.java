package com.jhops10.controle_estoque.domain.service;

import com.jhops10.controle_estoque.api.dto.StockMovementDTO;
import com.jhops10.controle_estoque.domain.model.MovementType;
import com.jhops10.controle_estoque.domain.model.Product;
import com.jhops10.controle_estoque.domain.model.StockMovement;
import com.jhops10.controle_estoque.domain.repository.ProductRepository;
import com.jhops10.controle_estoque.domain.repository.StockMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository, ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
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

        return stockMovementRepository.save(stockMovement);

    }
}
