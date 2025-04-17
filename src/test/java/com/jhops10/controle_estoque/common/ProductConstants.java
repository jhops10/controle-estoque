package com.jhops10.controle_estoque.common;

import com.jhops10.controle_estoque.api.dto.ProductDTO;
import com.jhops10.controle_estoque.domain.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductConstants {

    public static final Long PRODUCT_ID = 1L;

    public static final ProductDTO PRODUCT_DTO = new ProductDTO("Produto Teste",
            "Descrição Teste",
            5,
            new BigDecimal("10.00"),
            1L, 3);

    public static final Product PRODUCT = new Product(1L,
            PRODUCT_DTO.name(),
            PRODUCT_DTO.description(),
            PRODUCT_DTO.quantity(),
            PRODUCT_DTO.price(),
            SupplierConstants.SUPPLIER,
            PRODUCT_DTO.minimumStock());

    public static final List<Product> PRODUCT_LIST = List.of(PRODUCT);

}
