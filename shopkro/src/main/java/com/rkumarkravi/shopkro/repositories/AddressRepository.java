package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByBuyerId(Long buyerId);
}