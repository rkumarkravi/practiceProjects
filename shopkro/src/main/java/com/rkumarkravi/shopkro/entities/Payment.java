package com.rkumarkravi.shopkro.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ECOMM_payment", indexes = {
        @Index(name = "idx_payment_order_id", columnList = "order_id"),
        @Index(name = "idx_payment_status", columnList = "status")
})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Associated order

    @Column(name = "payment_mode", nullable = false, length = 50)
    private String paymentMode; // e.g., CREDIT_CARD, DEBIT_CARD, UPI, CASH_ON_DELIVERY

    @Column(name = "status", nullable = false, length = 50)
    private String status; // e.g., SUCCESS, FAILED, PENDING

    @Column(name = "transaction_id", length = 100)
    private String transactionId; // Unique ID for the payment transaction

    @Column(name = "payment_date")
    private LocalDateTime paymentDate; // Date of payment

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
