package com.example.demo.controller;

import com.example.demo.entity.Landlord;
import com.example.demo.repository.LandlordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/landlords")
public class LandlordController {

    private final LandlordRepository landlordRepository;

    public LandlordController(LandlordRepository landlordRepository) {
        this.landlordRepository = landlordRepository;
    }


    @GetMapping
    public String listLandlords(Model model) {
        model.addAttribute("landlords", landlordRepository.findAll());
        return "landlords";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("landlord", new Landlord());
        return "landlord-form";
    }


    @PostMapping("/save")
    public String saveLandlord(@ModelAttribute Landlord landlord) {
        landlordRepository.save(landlord);
        return "redirect:/landlords";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Landlord landlord = landlordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Landlord not found"));
        model.addAttribute("landlord", landlord);
        return "landlord-form";
    }


    @GetMapping("/delete/{id}")
    public String deleteLandlord(@PathVariable Long id) {
        landlordRepository.deleteById(id);
        return "redirect:/landlords";
    }
}