package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Apartment;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query("SELECT AVG(a.monthlyPrice) FROM Apartment a")
    Double findAveragePrice();

    @Query("SELECT MAX(a.monthlyPrice) FROM Apartment a")
    Double findMaxPrice();

    @Query("SELECT MIN(a.monthlyPrice) FROM Apartment a")
    Double findMinPrice();
}