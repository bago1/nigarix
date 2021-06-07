package com.portfolio.nigar.services;

import com.portfolio.nigar.entities.Email;
import com.portfolio.nigar.repos.EmailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class EmailReceiverService {
    private final EmailRepo repo;

    public void getEmail(Email email) {
        repo.save(email);
    }
}
