package com.neosoft.repository;

import com.neosoft.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    boolean existsByPositionName(String positionName);
}
