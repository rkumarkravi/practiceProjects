package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}