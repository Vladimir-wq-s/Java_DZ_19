package com.example.demo.controller;

import com.example.demo.entity.Rent;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.RentRepository;
import com.example.demo.service.BonusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rents")
public class RentController {

    private final RentRepository rentRepository;
    private final ApartmentRepository apartmentRepository;
    private final BonusService bonusService;

    public RentController(RentRepository rentRepository, ApartmentRepository apartmentRepository, BonusService bonusService) {
        this.rentRepository = rentRepository;
        this.apartmentRepository = apartmentRepository;
        this.bonusService = bonusService;
    }

    @GetMapping
    public String listRents(Model model) {

        model.addAttribute("rents", rentRepository.findAllByArchivedFalseOrderByRentalMonthsDesc());
        return "rents";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("rent", new Rent());
        model.addAttribute("apartments", apartmentRepository.findAllByArchivedFalseOrderByIsTopDesc());
        return "rent-form";
    }

    @PostMapping("/save")
    public String saveRent(@ModelAttribute Rent rent) {

        rentRepository.save(rent);

        bonusService.processRentBonus(rent);
        return "redirect:/rents";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rent not found"));
        model.addAttribute("rent", rent);
        model.addAttribute("apartments", apartmentRepository.findAllByArchivedFalseOrderByIsTopDesc());
        return "rent-form";
    }


    @GetMapping("/delete/{id}")
    public String archiveRent(@PathVariable Long id) {
        Rent rent = rentRepository.findById(id).orElseThrow();
        rent.setArchived(true);
        rentRepository.save(rent);
        return "redirect:/rents";
    }
}