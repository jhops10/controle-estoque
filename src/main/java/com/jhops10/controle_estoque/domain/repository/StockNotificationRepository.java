package com.jhops10.controle_estoque.domain.repository;

import com.jhops10.controle_estoque.domain.model.StockNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockNotificationRepository extends JpaRepository<StockNotification, Long> {
}
