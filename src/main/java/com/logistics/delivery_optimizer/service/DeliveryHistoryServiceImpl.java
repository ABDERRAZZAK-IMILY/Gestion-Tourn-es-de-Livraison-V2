package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.Model.Delivery;
import com.logistics.delivery_optimizer.Model.DeliveryHistory;
import com.logistics.delivery_optimizer.Model.Tour;
import com.logistics.delivery_optimizer.dto.DeliveryHistoryResponseDTO;
import com.logistics.delivery_optimizer.mapper.DeliveryHistoryMapper;
import com.logistics.delivery_optimizer.repository.DeliveryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryHistoryServiceImpl implements DeliveryHistoryService {

    private final DeliveryHistoryRepository historyRepository;
    private final DeliveryHistoryMapper historyMapper;

    @Autowired
    public DeliveryHistoryServiceImpl(DeliveryHistoryRepository historyRepository, DeliveryHistoryMapper historyMapper) {
        this.historyRepository = historyRepository;
        this.historyMapper = historyMapper;
    }

    @Override
    @Transactional
    public void createHistoryFromTour(Tour completedTour) {
        List<DeliveryHistory> historiesToSave = new ArrayList<>();

        for (Delivery delivery : completedTour.getDeliveries()) {

            LocalTime plannedTime = LocalTime.of(10, 0);
            LocalTime actualTime = LocalTime.now();

            long delay = ChronoUnit.MINUTES.between(plannedTime, actualTime);

            DeliveryHistory history = DeliveryHistory.builder()
                    .customer(delivery.getCustomer())
                    .tour(completedTour)
                    .deliveryDate(completedTour.getTourDate())
                    .plannedTime(plannedTime)
                    .actualTime(actualTime)
                    .delayInMinutes(delay > 0 ? delay : 0)
                    .dayOfWeek(completedTour.getTourDate().getDayOfWeek())
                    .build();

            historiesToSave.add(history);
        }

        historyRepository.saveAll(historiesToSave);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryHistoryResponseDTO> getAllHistory(Pageable pageable) {
        return historyRepository.findAll(pageable)
                .map(historyMapper::toResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public DeliveryHistoryResponseDTO getHistoryById(Long id) {
        DeliveryHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("History not found with id: " + id));
        return historyMapper.toResponseDTO(history);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryHistoryResponseDTO> searchByDateRange(LocalDate start, LocalDate end, Pageable pageable) {
        return historyRepository.findByDeliveryDateBetween(start, end, pageable)
                .map(historyMapper::toResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryHistoryResponseDTO> searchByCustomerId(Long customerId, Pageable pageable) {
        return historyRepository.findByCustomerId(customerId, pageable)
                .map(historyMapper::toResponseDTO);
    }
}