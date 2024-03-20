package com.kazimoto.digitalbackend.repository;

import com.kazimoto.digitalbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByRowId(String rowId);
}
