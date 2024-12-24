package com.rkumarkravi.shopkro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ECOMM_product", indexes = {
        @Index(name = "idx_product_name", columnList = "name"),
        @Index(name = "idx_seller_id", columnList = "seller_id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "min_order_qty_allowed")
    private Long minOrderQtyAllowed;

    @Column(name = "max_order_qty_allowed")
    private Long maxOrderQtyAllowed;

    @Column(name = "qty", nullable = false)
    private Long qty;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "mfd_date")
    private LocalDate mfdDate;

    @Column(name = "exp_date")
    private LocalDate expDate;

    @Column(name = "category", length = 100)
    private String category; // e.g., Electronics, Clothing, etc.

    @Column(name = "sku", unique = true, length = 50)
    private String sku; // Stock Keeping Unit, unique identifier for products

    @Column(name = "status", nullable = false, length = 20)
    private String status; // ACTIVE, OUT_OF_STOCK, DISCONTINUED, etc.

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
