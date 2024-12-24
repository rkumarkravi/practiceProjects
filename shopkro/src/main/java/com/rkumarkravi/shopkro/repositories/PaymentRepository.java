package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("select p from Payment p where p.order.id = ?1")
    Optional<Payment> findByOrderId(@NonNull Long id);

}