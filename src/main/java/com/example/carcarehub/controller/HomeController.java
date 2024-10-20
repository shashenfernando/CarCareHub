package com.example.carcarehub.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping
    public String home(Model model){
        return "Home";
    }

}
