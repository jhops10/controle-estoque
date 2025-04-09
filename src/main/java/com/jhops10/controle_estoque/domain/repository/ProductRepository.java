package com.jhops10.controle_estoque.domain.repository;

import com.jhops10.controle_estoque.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByQuantityLessThan(Integer quantity);

    @Query("SELECT p FROM Product p WHERE p.minimumStock IS NOT NULL AND p.quantity < p.minimumStock")
    List<Product> findProductsWithLowStock();
}
