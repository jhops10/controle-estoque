package com.jhops10.controle_estoque.domain.repository;


import com.jhops10.controle_estoque.domain.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
