package com.jhops10.controle_estoque.common;

import com.jhops10.controle_estoque.api.dto.SupplierDTO;
import com.jhops10.controle_estoque.domain.model.Supplier;

import java.util.List;

public class SupplierConstants {

    public static final SupplierDTO SUPPLIER_DTO = new SupplierDTO(
            "Fornecedor Teste",
            "fornecedor@email.com");

    public static final Supplier SUPPLIER = new Supplier(
            1L,
            SUPPLIER_DTO.name(),
            SUPPLIER_DTO.email());

    public static final List<Supplier> SUPPLIER_LIST = List.of(SUPPLIER);
}
