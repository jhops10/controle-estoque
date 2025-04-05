package com.jhops10.controle_estoque.api.controller;

import com.jhops10.controle_estoque.api.dto.ProductDTO;
import com.jhops10.controle_estoque.domain.model.Product;
import com.jhops10.controle_estoque.domain.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> registerProduct(@RequestBody @Valid ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.registerProduct(dto));
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @GetMapping("/quantity-under")
    public ResponseEntity<List<Product>> getItemsUnderQuantity(@RequestParam(defaultValue = "5") Integer limit) {
        return ResponseEntity.ok(productService.getItemsUnderQuantity(limit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
