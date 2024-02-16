package com.kazimoto.digitalbackend.repository;

import com.kazimoto.digitalbackend.entity.District;
import com.kazimoto.digitalbackend.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
}
