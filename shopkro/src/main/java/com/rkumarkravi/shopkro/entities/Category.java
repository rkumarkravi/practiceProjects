package com.rkumarkravi.shopkro.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ECOMM_CATEGORY")
@Getter
@Setter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated primary key
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "NAME", nullable = false, unique = true, length = 100)
    private String name; // Unique category name

    @Column(name = "DESCRIPTION", length = 500)
    private String description; // Optional category description

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status; // ACTIVE, INACTIVE, etc.

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Product> products = new HashSet<>(); // Relationship with Product

}
