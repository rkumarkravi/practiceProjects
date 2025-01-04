package com.rkumarkravi.shopkro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ECOMM_init_data")
public class InitData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String key;
    private String value;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "status", nullable = false)
    private String status; // ACTIVE, INACTIVE etc.

    @PrePersist
    protected void onCreate() {
        this.status="ACTIVE";
        this.createdAt = LocalDateTime.now();
    }
}