package com.jhops10.controle_estoque.domain.repository;

import com.jhops10.controle_estoque.domain.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByEmail(String email);
}
