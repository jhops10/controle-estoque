package com.jhops10.controle_estoque.domain.service;

import static com.jhops10.controle_estoque.common.ProductConstants.*;
import static com.jhops10.controle_estoque.common.SupplierConstants.SUPPLIER;

import com.jhops10.controle_estoque.domain.model.Product;
import com.jhops10.controle_estoque.domain.repository.ProductRepository;
import com.jhops10.controle_estoque.domain.repository.SupplierRepository;
import com.jhops10.controle_estoque.exceptions.ProductNotFoundException;
import com.jhops10.controle_estoque.exceptions.SupplierNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
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
        assertEquals(PRODUCT_ID, sut.getSupplier().getId());

        verify(supplierRepository).findById(PRODUCT_ID);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenSupplierNotFound() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SupplierNotFoundException.class, () -> productService.registerProduct(PRODUCT_DTO));

        verify(supplierRepository).findById(1L);
        verifyNoInteractions(productRepository);
    }

    @Test
    void shouldReturnProductWhenIdExists() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));

        Product sut = productService.findProductById(PRODUCT_ID);

        assertEquals("Produto Teste", sut.getName());
        assertEquals("Descrição Teste", sut.getDescription());
        assertEquals(5, sut.getQuantity());
        assertEquals(new BigDecimal("10.00"), sut.getPrice());
        assertEquals(1L, sut.getSupplier().getId());

        verify(productRepository).findById(PRODUCT_ID);
    }

    @Test
    void shouldThrowExceptionWhenProductIdDoesNotExist() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(PRODUCT_ID));

        verify(productRepository).findById(PRODUCT_ID);
    }


    @Test
    void shouldUpdateProductSuccessfully() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(SUPPLIER));
        when(productRepository.save(any(Product.class))).thenReturn(PRODUCT);

        Product sut = productService.updateProduct(PRODUCT.getId(), PRODUCT_DTO);

        assertNotNull(sut);
        assertEquals("Produto Teste", sut.getName());
        assertEquals("Descrição Teste", sut.getDescription());
        assertEquals(5, sut.getQuantity());
        assertEquals(new BigDecimal("10.00"), sut.getPrice());
        assertEquals(1L, sut.getSupplier().getId());

        verify(productRepository).findById(PRODUCT_ID);
        verify(supplierRepository).findById(SUPPLIER.getId());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenProductToUpdateNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(PRODUCT_ID, PRODUCT_DTO));

        verify(productRepository).findById(PRODUCT_ID);
        verifyNoInteractions(supplierRepository);
    }

    @Test
    void shouldThrowExceptionWhenSupplierNotFoundOnUpdate() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SupplierNotFoundException.class, () -> productService.updateProduct(PRODUCT_ID, PRODUCT_DTO));

        verify(productRepository).findById(PRODUCT_ID);
        verify(supplierRepository).findById(SUPPLIER.getId());
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void shouldDeleteProductSuccessfully() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));

        productService.deleteProduct(PRODUCT_ID);

        verify(productRepository).findById(PRODUCT_ID);
        verify(productRepository).deleteById(PRODUCT_ID);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingProduct() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(PRODUCT_ID));

        verify(productRepository).findById(PRODUCT_ID);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void shouldReturnAllProducts() {
        when(productRepository.findAll()).thenReturn(PRODUCT_LIST);

        List<Product> sut = productService.findAllProducts();

        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals("Produto Teste", sut.get(0).getName());

        verify(productRepository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoProductsExist() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<Product> sut = productService.findAllProducts();

        assertNotNull(sut);
        assertTrue(sut.isEmpty());

        verify(productRepository).findAll();
    }

    @Test
    void shouldReturnAllProductsWithLowStock() {
        when(productRepository.findProductsWithLowStock()).thenReturn(PRODUCT_LIST);

        List<Product> sut = productService.getProductsWithLowStock();

        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals("Produto Teste", sut.get(0).getName());

        verify(productRepository).findProductsWithLowStock();
    }

    @Test
    void shouldReturnEmptyListWhenNoProductsWithLowStockExist() {
        when(productRepository.findProductsWithLowStock()).thenReturn(Collections.emptyList());

        List<Product> sut = productService.getProductsWithLowStock();

        assertNotNull(sut);
        assertTrue(sut.isEmpty());

        verify(productRepository).findProductsWithLowStock();
    }

}