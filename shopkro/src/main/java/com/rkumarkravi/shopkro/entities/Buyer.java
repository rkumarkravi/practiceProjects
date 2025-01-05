package com.rkumarkravi.shopkro.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ECOMM_buyer", indexes = {
        @Index(name = "idx_buyer_email", columnList = "email"),
        @Index(name = "idx_buyer_mobNo", columnList = "mobNo")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Buyer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_id", nullable = false)
    private Long id;

    @Column(name = "buyer_name", nullable = false, length = 100)
    private String name;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "mob_no", nullable = false, unique = true, length = 15)
    private String mobNo;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "status", nullable = false, length = 20)
    private String status; // ACTIVE, INACTIVE, BANNED, etc.

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @OneToMany(mappedBy = "buyer", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("buyer-address")
    private Set<Address> addresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "buyer", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("buyer-orders")
    private Set<Order> orders = new LinkedHashSet<>();

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
