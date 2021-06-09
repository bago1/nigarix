package com.portfolio.nigar.repos;

import com.portfolio.nigar.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends JpaRepository<Email,Integer> {

}
