package com.portfolio.nigar.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Experience {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    String positionName;
    Integer startWorkYear;
    Integer endWorkYear;
    String companyName;
    String workSummary;
    String companyCity;


}
