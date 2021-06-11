package com.portfolio.nigar.controllers;

import com.portfolio.nigar.entities.Email;
import com.portfolio.nigar.repos.EmailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailRepo emailRepo;
    private final Email email;

    @GetMapping("/")
    public ModelAndView greetingForm(Model model) {
        model.addAttribute("email", email);
        ModelAndView md = new ModelAndView();
        md.setViewName("index");
        return md;
    }
    @PostMapping("/email")
    @CrossOrigin(origins = "http://localhost:8081")
    public ModelAndView sendEmail(Model model,@RequestBody  Email email) {
        System.out.println(email);
        model.addAttribute("email", email);
        emailRepo.save(email);
        ModelAndView md = new ModelAndView();
        md.setViewName("index");
        return md;
    }



}
