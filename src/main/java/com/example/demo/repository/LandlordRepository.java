package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Landlord;

import java.util.List;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Long> {
    List<Landlord> findAllByArchivedFalse();
    List<Landlord> findAllByArchivedTrue();
}