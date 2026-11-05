package com.example.demo.service;

import com.example.demo.entity.Landlord;
import com.example.demo.entity.Rent;
import com.example.demo.repository.LandlordRepository;
import com.example.demo.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class BonusService {

    private final RentRepository rentRepository;
    private final LandlordRepository landlordRepository;

    public BonusService(RentRepository rentRepository, LandlordRepository landlordRepository) {
        this.rentRepository = rentRepository;
        this.landlordRepository = landlordRepository;
    }

    public void processRentBonus(Rent rent) {

        if (rent.getStartDate() != null && rent.getEndDate() != null) {
            long months = ChronoUnit.MONTHS.between(rent.getStartDate(), rent.getEndDate());
            if (months < 1) months = 1;
            rent.setRentalMonths(months);
        }


        if (rent.getApartment() != null) {
            Landlord landlord = rent.getApartment().getLandlord();
            if (landlord != null) {
                long count = rentRepository.countByApartment_Landlord(landlord);
                landlord.setRentedApartmentsCount((int) count);
                landlordRepository.save(landlord);
            }
        }
    }

    public boolean canSetTop(Landlord landlord) {
        return landlord.getRentedApartmentsCount() >= 3;
    }
}