package com.rkumarkravi.shopkro.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "ECOMM_ADDRESS", indexes = {
        @Index(name = "idx_address_city", columnList = "city"),
        @Index(name = "idx_address_state", columnList = "state"),
        @Index(name = "idx_address_pinCode", columnList = "pinCode")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id", nullable = false)
    private Long id;

    @Column(name = "buyer_name", nullable = false, length = 100)
    private String buyerName;

    @Column(name = "mobile_no", nullable = false, length = 15)
    private String mobileNo;

    @Column(name = "locality", nullable = false, length = 255)
    private String locality; // Specific locality or neighborhood

    @Column(name = "area", length = 255)
    private String area; // Broader area, optional

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "state", nullable = false, length = 100)
    private String state;

    @Column(name = "pin_code", nullable = false, length = 10)
    private String pinCode;

    @Column(name = "landmark", length = 255)
    private String landMark; // Optional field to specify nearby landmark

    @Column(name = "alternate_mob_no", length = 15)
    private String alternateMobNo; // Optional alternate mobile number

    @Column(name = "type", nullable = false, length = 50)
    private String type; // HOME, WORK, OTHER

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Timestamp for address creation

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Timestamp for address updates

    @Column(name = "created_by", length = 50)
    private String createdBy; // User/system that created the address

    @Column(name = "updated_by", length = 50)
    private String updatedBy; // User/system that last updated the address

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer; // Buyer associated with this address

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
