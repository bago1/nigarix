package com.portfolio.nigar.controllers;

import com.portfolio.nigar.entities.Email;
import com.portfolio.nigar.services.EmailReceiverService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequiredArgsConstructor
public class EmailReceiverApi {
    private final EmailReceiverService service;

    @PostMapping("/")
    public void receiveEmail(@RequestBody Email email) {
        System.out.println(email);
        service.getEmail(email);
    }

}
