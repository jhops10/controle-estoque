package com.jhops10.controle_estoque.domain.service;

import static com.jhops10.controle_estoque.common.ProductConstants.PRODUCT;

import static com.jhops10.controle_estoque.common.StockMovementConstants.STOCK_MOVEMENT_DTO;


import com.jhops10.controle_estoque.domain.model.StockMovement;
import com.jhops10.controle_estoque.domain.repository.ProductRepository;
import com.jhops10.controle_estoque.domain.repository.StockMovementRepository;
import com.jhops10.controle_estoque.domain.repository.StockNotificationRepository;
import com.jhops10.controle_estoque.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockMovementServiceTest {

    @InjectMocks
    private StockMovementService stockMovementService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockNotificationRepository stockNotificationRepository;

    @Mock
    private StockMovementRepository stockMovementRepository;

    @Mock
    private EmailService emailService;


    @Test
    void shouldRegisterIncomingStockSuccessfully() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(PRODUCT));
        when(stockMovementRepository.save(any(StockMovement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StockMovement sut = stockMovementService.registerMovement(STOCK_MOVEMENT_DTO);

        assertNotNull(sut);
        assertEquals(10, PRODUCT.getQuantity());

        verify(productRepository).save(PRODUCT);
        verify(stockMovementRepository).save(any(StockMovement.class));
        verifyNoMoreInteractions(emailService);
        verifyNoMoreInteractions(stockNotificationRepository);
    }

    @Test
    void shouldRegisterOutgoingStockSuccessfully() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(PRODUCT));
        when(stockMovementRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        StockMovement result = stockMovementService.registerMovement(STOCK_MOVEMENT_DTO);

        assertNotNull(result);
        assertEquals(10, PRODUCT.getQuantity());

        verify(productRepository).save(PRODUCT);
        verify(stockMovementRepository).save(any());
        verifyNoInteractions(emailService);
        verifyNoInteractions(stockNotificationRepository);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> stockMovementService.registerMovement(STOCK_MOVEMENT_DTO));

        verify(productRepository).findById(1L);
        verifyNoMoreInteractions(productRepository);
    }



}