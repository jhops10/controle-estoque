package com.jhops10.controle_estoque.domain.service;

import com.jhops10.controle_estoque.common.SupplierConstants;
import com.jhops10.controle_estoque.domain.model.Supplier;
import com.jhops10.controle_estoque.domain.repository.SupplierRepository;
import com.jhops10.controle_estoque.exceptions.SupplierNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.jhops10.controle_estoque.common.SupplierConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {

    @InjectMocks
    private SupplierService supplierService;

    @Mock
    private SupplierRepository supplierRepository;


    @Test
    void shouldSaveSupplierWithSuccess() {
        when(supplierRepository.save(any(Supplier.class))).thenReturn(SUPPLIER);

        Supplier sut = supplierService.registerSupplier(SUPPLIER_DTO);

        assertNotNull(sut);
        assertEquals(1L, sut.getId());
        assertEquals("Fornecedor Teste", sut.getName());
        assertEquals("fornecedor@email.com", sut.getEmail());

        verify(supplierRepository).save(any(Supplier.class));
    }

    @Test
    void shouldReturnSupplierWhenIdExists() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(SUPPLIER));

        Supplier sut = supplierService.getSupplierById(1L);

        assertNotNull(sut);
        assertEquals("Fornecedor Teste", sut.getName());
        assertEquals("fornecedor@email.com", sut.getEmail());

        verify(supplierRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenSupplierIdDoesNotExists() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SupplierNotFoundException.class, () -> supplierService.getSupplierById(2L));

        verify(supplierRepository).findById(anyLong());
    }

    @Test
    void shouldReturnAllSuppliers() {
        when(supplierRepository.findAll()).thenReturn(SUPPLIER_LIST);

        List<Supplier> sut = supplierService.getAllSuppliers();

        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals("Fornecedor Teste", sut.get(0).getName());
        assertEquals("fornecedor@email.com", sut.get(0).getEmail());

        verify(supplierRepository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoSupplierExists() {
        when(supplierRepository.findAll()).thenReturn(Collections.emptyList());

        List<Supplier> sut = supplierService.getAllSuppliers();

        assertNotNull(sut);
        assertEquals(0, sut.size());
        assertTrue(sut.isEmpty());

        verify(supplierRepository).findAll();
    }

    @Test
    void shouldReturnSupplierWhenEmailExists() {
        when(supplierRepository.findByEmail(anyString())).thenReturn(Optional.of(SUPPLIER));

        Supplier sut = supplierService.getSupplierByEmail("fornecedor@email.com");

        assertNotNull(sut);
        assertEquals("Fornecedor Teste", sut.getName());
        assertEquals("fornecedor@email.com", sut.getEmail());

        verify(supplierRepository).findByEmail("fornecedor@email.com");
    }

    @Test
    void shouldThrowExceptionWhenSupplierEmailDoesNotExists() {
        when(supplierRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(SupplierNotFoundException.class, () -> supplierService.getSupplierByEmail("email@email.com"));

        verify(supplierRepository).findByEmail("email@email.com");
    }

    @Test
    void shouldDeleteSupplierSuccessfully() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(SUPPLIER));

        supplierService.deleteSupplierById(1L);

        verify(supplierRepository).findById(1L);
        verify(supplierRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExestingSupplier() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SupplierNotFoundException.class, () -> supplierService.deleteSupplierById(1L));

        verify(supplierRepository).findById(1L);
        verifyNoMoreInteractions(supplierRepository);
    }

}