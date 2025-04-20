package com.jhops10.controle_estoque.domain.service;


import com.jhops10.controle_estoque.api.dto.StockNotificationDTO;
import com.jhops10.controle_estoque.common.StockNotificationConstants;
import com.jhops10.controle_estoque.domain.model.StockNotification;
import com.jhops10.controle_estoque.domain.repository.StockNotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.jhops10.controle_estoque.common.StockNotificationConstants.STOCK_NOTIFICATION;
import static com.jhops10.controle_estoque.common.StockNotificationConstants.STOCK_NOTIFICATION_LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockNotificationServiceTest {

    @InjectMocks
    private StockNotificationService stockNotificationService;

    @Mock
    private StockNotificationRepository stockNotificationRepository;


    @Test
    void shouldReturnAllStockNotifications() {
        when(stockNotificationRepository.findAll()).thenReturn(STOCK_NOTIFICATION_LIST);

        List<StockNotificationDTO> sut = stockNotificationService.listAll();

        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals(1L, sut.get(0).id());
        assertEquals("Produto Teste", sut.get(0).productName());
        assertEquals("Mensagem Notoficação", sut.get(0).message());
        assertEquals(STOCK_NOTIFICATION.getDate(), sut.get(0).date());

        verify(stockNotificationRepository).findAll();
        verifyNoMoreInteractions(stockNotificationRepository);

    }

    @Test
    void shouldReturnEmptyListWhenNoStockNotificationExists() {
        when(stockNotificationRepository.findAll()).thenReturn(Collections.emptyList());

        List<StockNotificationDTO> sut = stockNotificationService.listAll();

        assertNotNull(sut);
        assertEquals(0, sut.size());
        assertTrue(sut.isEmpty());
    }

}