package com.rkumarkravi.shopkro.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ECOMM_seller", indexes = {
        @Index(name = "idx_seller_name", columnList = "sellerName"),
        @Index(name = "idx_seller_gst", columnList = "sellerGst")
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Seller implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    @Column(name = "seller_name", nullable = false, length = 100)
    private String name;

    @Column(name = "seller_address", length = 255)
    private String sellerAddress;

    @Column(name = "seller_gst", unique = true, length = 15)
    private String sellerGst;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    private String status; // ACTIVE, INACTIVE, BANNED, etc.

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "gender", length = 10)
    private String gender;

    @OneToMany(mappedBy = "seller", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude // Lombok annotation to set a default value for the field in the builder
    private Set<Product> products = new LinkedHashSet<>();

    @Column(name = "pwd", length = 255)
    @JsonIgnore
    private String password;

    @PrePersist
    protected void onCreate() {
        this.status="ACTIVE";
        this.createdBy="SYSTEM";
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.id);
    }
}
