package com.portfolio.nigar.controllers;

import com.portfolio.nigar.entities.Email;
import com.portfolio.nigar.repos.EmailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.CrossOrigin;

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

//    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/email")
    public ModelAndView sendEmail(Model model,Email email) {

        model.addAttribute("email", email);
        emailRepo.save(email);
        ModelAndView md = new ModelAndView();
        md.setViewName("index");
        return md;
    }



}
