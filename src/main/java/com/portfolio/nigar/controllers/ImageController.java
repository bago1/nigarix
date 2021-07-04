package com.portfolio.nigar.controllers;

import com.portfolio.nigar.entities.PhotoType;
import com.portfolio.nigar.exceptions.ImageNotFoundException;
import com.portfolio.nigar.services.ImageService;
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
import java.util.Map;
import java.util.Objects;

import static com.portfolio.nigar.entities.PhotoType.OTHER_PHOTO;
import static com.portfolio.nigar.entities.PhotoType.PROFILE_PHOTO;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ImageController {
    private final ImageService imageService;
    private final UploadFileRepo repo;


    @PostMapping("/submitphoto")
    @CrossOrigin(origins = "http://localhost:8081")
    public RedirectView addBcgPhoto(@RequestParam("file") MultipartFile multipartFile,
                                    UploadFile uploadFile,
                                    @RequestParam("type") Integer type) throws IOException {
        System.out.println(type);
        imageService.uploadPhoto(multipartFile, uploadFile, type);

        return new RedirectView("/", true);
    }

    @GetMapping(value = "/getavatar",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public String getProfilePhoto() throws IOException {

        InputStream is = getClass()
                .getResourceAsStream(repo.getUploadFileByPhotoType(PROFILE_PHOTO)
                        .orElseThrow(ImageNotFoundException::new)
                        .getPhotosImagePath());
        byte[] bytes = IOUtils.toByteArray(is);
        String encoded = java.util.Base64.getEncoder().encodeToString(bytes);

        return encoded;
    }

    @GetMapping(value = "/getbcgphoto",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public String getBcgPhoto() throws IOException {

        InputStream is = getClass()
                .getResourceAsStream(repo.getUploadFileByPhotoType(OTHER_PHOTO)
                        .orElseThrow(ImageNotFoundException::new)
                        .getPhotosImagePath());
        byte[] bytes = IOUtils.toByteArray(is);
        String encoded = java.util.Base64.getEncoder().encodeToString(bytes);

        return encoded;
    }

}
