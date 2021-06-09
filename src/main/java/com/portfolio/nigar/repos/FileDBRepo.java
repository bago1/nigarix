package com.portfolio.nigar.repos;

import com.portfolio.nigar.entities.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileDBRepo extends JpaRepository<FileDB, String> {

}