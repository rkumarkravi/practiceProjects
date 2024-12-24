package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.Order;
import com.rkumarkravi.shopkro.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order getOrderById(String id) {
        return orderRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(String id, Order updatedOrder) {
        Order order = getOrderById(id);
        order.setStateId(updatedOrder.getStateId());
        order.setAddress(updatedOrder.getAddress());
        order.setQty(updatedOrder.getQty());
        return orderRepository.save(order);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(Long.valueOf(id));
    }
}

