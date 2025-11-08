package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.Model.Tour;
import com.logistics.delivery_optimizer.dto.DeliveryHistoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface DeliveryHistoryService {


    Page<DeliveryHistoryResponseDTO> getAllHistory(Pageable pageable);


    DeliveryHistoryResponseDTO getHistoryById(Long id);


    Page<DeliveryHistoryResponseDTO> searchByDateRange(LocalDate start, LocalDate end, Pageable pageable);


    Page<DeliveryHistoryResponseDTO> searchByCustomerId(Long customerId, Pageable pageable);


    void createHistoryFromTour(Tour completedTour);
}