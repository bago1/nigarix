package com.portfolio.nigar.entities;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Email {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    Integer id;
    String fullname;
    String email;
    String subject;
    String message;

}
