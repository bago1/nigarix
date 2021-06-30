package com.portfolio.nigar.services;

import com.portfolio.nigar.entities.UploadFile;
import com.portfolio.nigar.repos.UploadFileRepo;
import com.portfolio.nigar.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

import static com.portfolio.nigar.entities.PhotoType.PROFILE_PHOTO;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final UploadFileRepo repo;
    private final String UPLOAD_DIR = "./src/main/resources/static/assets/img/";


    public void setProfilePhoto(MultipartFile multipartFile, UploadFile uploadFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        System.out.println(fileName);
        uploadFile.setPhotos(fileName);
        uploadFile.setPhotoType(PROFILE_PHOTO);
        UploadFile savedFile;
        if (repo.existsUploadFileByPhotoType(PROFILE_PHOTO)) {
            UploadFile var = repo.findUploadFileByPhotoType(PROFILE_PHOTO);
            var.setPhotos(fileName);
            var.setPhotoType(PROFILE_PHOTO);
            savedFile = repo.save(var);

        } else {
            savedFile = repo.save(uploadFile);
        }
        String uploadDir = UPLOAD_DIR + savedFile.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }

    public void setOtherPhoto(MultipartFile multipartFile, UploadFile uploadFile) {
    }
}
