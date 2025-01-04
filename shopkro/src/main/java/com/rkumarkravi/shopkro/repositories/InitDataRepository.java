package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.InitData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface InitDataRepository extends JpaRepository<InitData, Long> {
    @Query("select i from InitData i where i.status = 'ACTIVE' order by i.createdAt")
    Set<InitData> findByStatusOrderByCreatedAtAsc();
}