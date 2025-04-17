package com.jhops10.controle_estoque.domain.service;

import static com.jhops10.controle_estoque.common.ProductConstants.PRODUCT_DTO;
import static com.jhops10.controle_estoque.common.SupplierConstants.SUPPLIER;
import static com.jhops10.controle_estoque.common.ProductConstants.PRODUCT;

import com.jhops10.controle_estoque.domain.model.Product;
import com.jhops10.controle_estoque.domain.repository.ProductRepository;
import com.jhops10.controle_estoque.domain.repository.SupplierRepository;
import com.jhops10.controle_estoque.exceptions.SupplierNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    void shouldSaveProductWithSuccess() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(SUPPLIER));
        when(productRepository.save(any(Product.class))).thenReturn(PRODUCT);

        Product sut = productService.registerProduct(PRODUCT_DTO);

        assertNotNull(sut);
        assertEquals("Produto Teste", sut.getName());
        assertEquals("Descrição Teste", sut.getDescription());
        assertEquals(5, sut.getQuantity());
        assertEquals(new BigDecimal("10.00"), sut.getPrice());
        assertEquals(1L, sut.getSupplier().getId());

        verify(supplierRepository).findById(1L);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenSupplierNotFound() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SupplierNotFoundException.class, () -> productService.registerProduct(PRODUCT_DTO));

        verify(supplierRepository).findById(1L);
        verifyNoInteractions(productRepository);
    }

    
}