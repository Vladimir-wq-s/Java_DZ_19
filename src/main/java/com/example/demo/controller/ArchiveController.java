package com.example.demo.controller;

import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.LandlordRepository;
import com.example.demo.repository.RentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveController {

    private final LandlordRepository landlordRepository;
    private final ApartmentRepository apartmentRepository;
    private final RentRepository rentRepository;

    public ArchiveController(LandlordRepository landlordRepository,
                             ApartmentRepository apartmentRepository,
                             RentRepository rentRepository) {
        this.landlordRepository = landlordRepository;
        this.apartmentRepository = apartmentRepository;
        this.rentRepository = rentRepository;
    }

    @GetMapping("/archive")
    public String showArchive(Model model) {
        model.addAttribute("landlords", landlordRepository.findAllByArchivedTrue());
        model.addAttribute("apartments", apartmentRepository.findAllByArchivedTrue());
        model.addAttribute("rents", rentRepository.findAllByArchivedTrue());
        return "archive";
    }
}