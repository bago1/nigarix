package com.portfolio.nigar.repos;

import com.portfolio.nigar.entities.Email;
import com.portfolio.nigar.entities.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepo extends JpaRepository<UploadFile,Integer> {

    @Query(value = "select * from upload_file u where u.photo_type=profile_photo",nativeQuery = true)
     UploadFile findByType();
}
