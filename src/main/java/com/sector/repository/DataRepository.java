package com.sector.repository;

import com.sector.model.DataSectors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataRepository extends JpaRepository<DataSectors, String> {

    DataSectors findBySectorName(String value);

    boolean existsBySectorValue(String sectorValue);

    @Query("SELECT d FROM DataSectors d WHERE d.sectorName IN :sectorName")
    List<DataSectors> findAllBySectorName(List<String> sectorName);
}
