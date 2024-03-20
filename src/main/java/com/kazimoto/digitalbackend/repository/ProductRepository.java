package com.kazimoto.digitalbackend.repository;

import com.kazimoto.digitalbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByRowId(String rowId);
}
