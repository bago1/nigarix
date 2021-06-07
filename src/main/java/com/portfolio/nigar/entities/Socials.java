package com.portfolio.nigar.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Socials extends Person {

    @Id
    Integer id;
    String emailAddress;
    String fbAddress;
    String instagramAddress;
    String behanceAddress;
    String linkedinAddress;
    String mediumAddress;
    String email;
    String phoneNumber;


}
