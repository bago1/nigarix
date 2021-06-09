package com.portfolio.nigar.controllers;

import com.portfolio.nigar.entities.Email;
import com.portfolio.nigar.repos.EmailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailRepo repo;
    private final Email email;

    @GetMapping("/")
    public ModelAndView greetingForm(Model model) {
        model.addAttribute("email", email);
        ModelAndView md = new ModelAndView();
        md.setViewName("index");
        return md;
    }

    @PostMapping("/index")
    public ModelAndView sendEmail(Model model, Email email) {
        repo.save(email);
        model.addAttribute("email", email);
        ModelAndView md = new ModelAndView();
        md.setViewName("index");
        return md;
    }

}
