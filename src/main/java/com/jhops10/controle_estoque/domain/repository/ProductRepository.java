package com.jhops10.controle_estoque.domain.repository;

import com.jhops10.controle_estoque.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByQuantityLessThan(Integer quantity);
}
