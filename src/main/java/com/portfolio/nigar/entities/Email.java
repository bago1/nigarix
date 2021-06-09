package com.portfolio.nigar.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Component
@RequiredArgsConstructor
public class Email {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    Integer id;
    String fullname;
    String emailAddress;
    String subject;
    String message;

}
