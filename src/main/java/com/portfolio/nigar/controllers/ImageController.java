package com.portfolio.nigar.controllers;

import com.portfolio.nigar.entities.PhotoType;
import com.portfolio.nigar.exceptions.ImageNotFoundException;
import javassist.NotFoundException;
import org.apache.commons.io.IOUtils;
import com.portfolio.nigar.entities.UploadFile;
import com.portfolio.nigar.repos.UploadFileRepo;
import com.portfolio.nigar.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static com.portfolio.nigar.entities.PhotoType.PROFILE_PHOTO;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ImageController {
    private final UploadFileRepo repo;
    private final String UPLOAD_DIR = "./src/main/resources/static/assets/img/";

    @PostMapping("/updateavatar")
    @CrossOrigin(origins = "http://localhost:8081")
    public RedirectView addPhoto(@RequestParam("file") MultipartFile multipartFile,
                                 UploadFile uploadFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
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
        return new RedirectView("/", true);


    }

    @GetMapping(value = "/getavatar",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public String getphoto() throws IOException {

        InputStream is =  getClass()
                .getResourceAsStream(repo.getUploadFileByPhotoType(PROFILE_PHOTO)
                .orElseThrow(ImageNotFoundException::new)
                .getPhotosImagePath());
        byte[] bytes = IOUtils.toByteArray(is);
        String encoded = java.util.Base64.getEncoder().encodeToString(bytes);

        return encoded;
    }

}
