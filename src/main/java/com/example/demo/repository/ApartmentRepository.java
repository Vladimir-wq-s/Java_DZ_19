package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Apartment;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> findAllByArchivedFalseOrderByIsTopDesc();
    List<Apartment> findAllByArchivedTrue();

    @Query("SELECT AVG(a.monthlyPrice) FROM Apartment a WHERE a.archived = false")
    Double findAveragePrice();

    @Query("SELECT MAX(a.monthlyPrice) FROM Apartment a WHERE a.archived = false")
    Double findMaxPrice();

    @Query("SELECT MIN(a.monthlyPrice) FROM Apartment a WHERE a.archived = false")
    Double findMinPrice();
}