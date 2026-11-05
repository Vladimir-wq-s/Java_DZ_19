package com.example.demo.controller;

import com.example.demo.entity.Apartment;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.LandlordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {

    private final ApartmentRepository apartmentRepository;
    private final LandlordRepository landlordRepository;

    public ApartmentController(ApartmentRepository apartmentRepository, LandlordRepository landlordRepository) {
        this.apartmentRepository = apartmentRepository;
        this.landlordRepository = landlordRepository;
    }

    @GetMapping
    public String listApartments(Model model) {

        model.addAttribute("apartments", apartmentRepository.findAllByArchivedFalseOrderByIsTopDesc());
        return "apartments";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("apartment", new Apartment());
        model.addAttribute("landlords", landlordRepository.findAllByArchivedFalse());
        return "apartment-form";
    }

    @PostMapping("/save")
    public String saveApartment(@ModelAttribute Apartment apartment) {
        apartmentRepository.save(apartment);
        return "redirect:/apartments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));
        model.addAttribute("apartment", apartment);
        model.addAttribute("landlords", landlordRepository.findAllByArchivedFalse());
        return "apartment-form";
    }


    @GetMapping("/delete/{id}")
    public String archiveApartment(@PathVariable Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow();
        apartment.setArchived(true);
        apartmentRepository.save(apartment);
        return "redirect:/apartments";
    }
}