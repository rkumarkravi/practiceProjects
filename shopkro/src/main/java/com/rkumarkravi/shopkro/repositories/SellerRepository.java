package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query("select s from Seller s where upper(s.email) = upper(?1)")
    Optional<Seller> findByEmailIgnoreCase(@NonNull String email);

    @Query("select (count(s) > 0) from Seller s where upper(s.email) = upper(?1)")
    boolean existsByEmail(@NonNull String email);


}