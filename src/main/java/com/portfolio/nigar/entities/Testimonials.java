package com.portfolio.nigar.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Testimonials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String recommenderFullname;
    String recommenderPosition;
    String recommendText;

}
