package com.rkumarkravi.shopkro.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ECOMM_ORDER", indexes = {
        @Index(name = "idx_order_state", columnList = "stateId"),
        @Index(name = "idx_buyer_id", columnList = "buyer_id")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "state_id", nullable = false, length = 50)
    private String stateId; // Represents the state of the order (e.g., PLACED, SHIPPED, DELIVERED, CANCELLED)

    @Column(name = "address", nullable = false, length = 500)
    private String address; // Delivery address for the order

    @Column(name = "qty", nullable = false)
    private Long qty; // Total quantity of products in the order

    @Column(name = "total_price", nullable = false)
    private Float totalPrice; // Total price for the order

    @Column(name = "discount_applied")
    private Float discountApplied; // Total discount applied on the order

    @Column(name = "status", nullable = false, length = 50)
    private String status; // Order status (e.g., PENDING, COMPLETED, FAILED)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Timestamp for order creation

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Timestamp for order updates

    @Column(name = "created_by", length = 50)
    private String createdBy; // User/system that created the order

    @Column(name = "updated_by", length = 50)
    private String updatedBy; // User/system that last updated the order

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "ECOMM_ORDER_PRODUCTS",
            joinColumns = @JoinColumn(name = "order_order_id"),
            inverseJoinColumns = @JoinColumn(name = "products_product_id"))
    private Set<Product> products = new LinkedHashSet<>(); // Products associated with the order

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer; // Buyer associated with the order

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

