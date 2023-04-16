package com.leemore.orderservice.repository;

import com.leemore.orderservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
