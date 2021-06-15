package com.portfolio.nigar.controllers;

import com.portfolio.nigar.entities.PhotoType;
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

    @PostMapping("/changeava")
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
             savedFile =  repo.save(var);

        } else {
             savedFile = repo.save(uploadFile);
        }
            String uploadDir = UPLOAD_DIR + savedFile.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return new RedirectView("/", true);


    }



//    //works good uploads photo
//    @PostMapping("/uploads")
//    @CrossOrigin(origins = "http://localhost:8081")
//    public RedirectView addPhoto(@RequestParam("file") MultipartFile multipartFile,
//                                 UploadFile uploadFile) throws IOException {
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//        uploadFile.setPhotos(fileName);
//        uploadFile.setPhotoType(UploadFile.PhotoType.PROFILE_PHOTO);
//
//
//        UploadFile savedFile = repo.save(uploadFile);
//        String uploadDir = UPLOAD_DIR + savedFile.getId();
//        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//        return new RedirectView("/", true);
//    }


    @GetMapping(value = "/getphoto/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public String getphoto(@PathVariable int id) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream(repo.getById(id).getPhotosImagePath());
        System.out.println("gettttttt");
        byte[] bytes = IOUtils.toByteArray(in);
        String encoded = java.util.Base64.getEncoder().encodeToString(bytes);

        return encoded;
    }

}
