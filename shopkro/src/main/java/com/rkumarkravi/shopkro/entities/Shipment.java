package com.rkumarkravi.shopkro.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "ECOMM_shipment", indexes = {
        @Index(name = "idx_shipment_order_id", columnList = "order_id"),
        @Index(name = "idx_shipment_status", columnList = "status")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id", nullable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Associated order

    @Column(name = "carrier_name", length = 100)
    private String carrierName; // Shipping carrier (e.g., FedEx, UPS, etc.)

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber; // Tracking number for shipment

    @Column(name = "status", nullable = false, length = 50)
    private String status; // e.g., PENDING, SHIPPED, DELIVERED, RETURNED

    @Column(name = "shipped_date")
    private LocalDateTime shippedDate; // Date of shipment

    @Column(name = "expected_delivery_date")
    private LocalDateTime expectedDeliveryDate; // Expected delivery date

    @Column(name = "delivered_date")
    private LocalDateTime deliveredDate; // Date of delivery (if delivered)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
