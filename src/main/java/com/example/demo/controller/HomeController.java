package com.example.demo.controller;

import com.example.demo.repository.ApartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ApartmentRepository apartmentRepository;

    public HomeController(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("avgPrice", apartmentRepository.findAveragePrice());
        model.addAttribute("maxPrice", apartmentRepository.findMaxPrice());
        model.addAttribute("minPrice", apartmentRepository.findMinPrice());
        return "index";
    }
}