package com.logistics.delivery_optimizer.Controller;

import com.logistics.delivery_optimizer.dto.DeliveryHistoryResponseDTO;
import com.logistics.delivery_optimizer.service.DeliveryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/history")
public class DeliveryHistoryController {

    private final DeliveryHistoryService historyService;

    @Autowired
    public DeliveryHistoryController(DeliveryHistoryService historyService) {
        this.historyService = historyService;
    }


    @GetMapping
    public ResponseEntity<Page<DeliveryHistoryResponseDTO>> getAllHistory(
            @PageableDefault(size = 20, page = 0) Pageable pageable) {
        Page<DeliveryHistoryResponseDTO> historyPage = historyService.getAllHistory(pageable);
        return ResponseEntity.ok(historyPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryHistoryResponseDTO> getHistoryById(@PathVariable Long id) {
        DeliveryHistoryResponseDTO history = historyService.getHistoryById(id);
        return ResponseEntity.ok(history);
    }


    @GetMapping("/search/customer")
    public ResponseEntity<Page<DeliveryHistoryResponseDTO>> searchByCustomer(
            @RequestParam Long customerId,
            @PageableDefault(size = 20, page = 0) Pageable pageable) {
        Page<DeliveryHistoryResponseDTO> historyPage = historyService.searchByCustomerId(customerId, pageable);
        return ResponseEntity.ok(historyPage);
    }

    @GetMapping("/search/date")
    public ResponseEntity<Page<DeliveryHistoryResponseDTO>> searchByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @PageableDefault(size = 20, page = 0) Pageable pageable) {
        Page<DeliveryHistoryResponseDTO> historyPage = historyService.searchByDateRange(startDate, endDate, pageable);
        return ResponseEntity.ok(historyPage);
    }
}