package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Landlord;
import com.example.demo.entity.Rent;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findAllByArchivedFalseOrderByRentalMonthsDesc();
    List<Rent> findAllByArchivedTrue();

    long countByApartment_Landlord(Landlord landlord);
}