package com.portfolio.nigar.repos;

import com.portfolio.nigar.entities.Email;
import com.portfolio.nigar.entities.PhotoType;
import com.portfolio.nigar.entities.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepo extends JpaRepository<UploadFile,Integer> {

    boolean existsUploadFileByPhotoType(PhotoType PhotoType);

    UploadFile findUploadFileByPhotoType(PhotoType PhotoType);
}
