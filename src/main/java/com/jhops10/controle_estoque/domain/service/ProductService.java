package com.jhops10.controle_estoque.domain.service;

import com.jhops10.controle_estoque.api.dto.ProductDTO;
import com.jhops10.controle_estoque.domain.model.Product;
import com.jhops10.controle_estoque.domain.model.Supplier;
import com.jhops10.controle_estoque.domain.repository.ProductRepository;
import com.jhops10.controle_estoque.domain.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Product registerProduct(ProductDTO dto) {
        Supplier supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor com o id " + dto.supplierId() + " não encontrado."));

        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setQuantity(dto.quantity());
        product.setPrice(dto.price());
        product.setSupplier(supplier);
        product.setMinimumStock(dto.minimumStock());

        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, ProductDTO dto) {
        Product product = findProductById(id);

        Supplier supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor com id " + dto.supplierId() + " não encontrado."));


        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setQuantity(dto.quantity());
        product.setPrice(dto.price());
        product.setSupplier(supplier);

        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product produto = findProductById(id);
        productRepository.deleteById(id);
    }

    public List<Product> getItemsUnderQuantity(Integer quantity) {
        return productRepository.findByQuantityLessThan(quantity);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto com o id " + id + " não encontrado."));
    }


}
