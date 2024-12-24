package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}