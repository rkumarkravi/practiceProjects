package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    @Query("select b from Buyer b where upper(b.email) = upper(?1)")
    Optional<Buyer> findByEmailIgnoreCase(@NonNull String email);

    @Query("select (count(b) > 0) from Buyer b where upper(b.email) = upper(?1)")
    boolean existsByEmail(@NonNull String email);
}