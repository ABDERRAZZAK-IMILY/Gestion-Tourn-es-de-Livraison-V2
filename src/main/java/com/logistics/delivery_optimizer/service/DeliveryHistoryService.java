package com.logistics.delivery_optimizer.service;

public interface DeliveryHistoryService {

    void saveDeliveryHistory(Long deliveryId, Long vehicleId);
    void saveDeliveryHistory(Long deliveryId, Long vehicleId, Long warehouseId);
    void saveDeliveryHistory(Long deliveryId, Long vehicleId, Long warehouseId, Long customerId);

}
