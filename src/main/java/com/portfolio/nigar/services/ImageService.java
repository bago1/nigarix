package com.portfolio.nigar.services;

import com.portfolio.nigar.entities.PhotoType;
import com.portfolio.nigar.entities.UploadFile;
import com.portfolio.nigar.exceptions.UploadPhotoTypeNotMatchesException;
import com.portfolio.nigar.repos.UploadFileRepo;
import com.portfolio.nigar.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

import static com.portfolio.nigar.entities.PhotoType.OTHER_PHOTO;
import static com.portfolio.nigar.entities.PhotoType.PROFILE_PHOTO;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final UploadFileRepo repo;
    private final String UPLOAD_DIR = "./src/main/resources/static/assets/img/";



    public void uploadPhoto(MultipartFile multipartFile,
                            UploadFile uploadFile,
                            Integer type) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        PhotoType photoType = null;
        if(type==1) {
            photoType=PROFILE_PHOTO;
        }else if (type==2){
            photoType=OTHER_PHOTO;
        }else throw new UploadPhotoTypeNotMatchesException();

        uploadFile.setPhotos(fileName);
        uploadFile.setPhotoType(photoType);
        UploadFile savedFile;
        if (repo.existsUploadFileByPhotoType(photoType)) {
            UploadFile var = repo.findUploadFileByPhotoType(photoType);
            var.setPhotos(fileName);
            var.setPhotoType(photoType);
            savedFile = repo.save(var);
        } else {
            savedFile = repo.save(uploadFile);
        }
        String uploadDir = UPLOAD_DIR + savedFile.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }




}
