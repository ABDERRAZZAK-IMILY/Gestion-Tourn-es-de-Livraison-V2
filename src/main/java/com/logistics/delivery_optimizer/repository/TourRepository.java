package com.logistics.delivery_optimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.delivery_optimizer.Model.Tour;

public interface TourRepository extends JpaRepository<Tour , Long> {
}
