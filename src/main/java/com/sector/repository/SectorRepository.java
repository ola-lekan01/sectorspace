package com.sector.repository;

import com.sector.model.UserSector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectorRepository extends JpaRepository<UserSector, String> {
    List<UserSector> findAllByUser_Id(String id);
    Optional<UserSector> findByUser_Id(String id);
}
