package com.portfolio.nigar.controllers;

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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ImageController {
    private final UploadFileRepo repo;
    private final String UPLOAD_DIR = "./src/main/resources/uploads/";

    @PostMapping("/uploads")
    @CrossOrigin(origins = "http://localhost:8081")
    public RedirectView addPhoto(@RequestParam("file") MultipartFile multipartFile,
                                 UploadFile uploadFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        uploadFile.setPhotos(fileName);
        UploadFile savedFile = repo.save(uploadFile);
        String uploadDir = "./src/main/resources/static/assets/img/" + savedFile.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return new RedirectView("/", true);
    }

    @GetMapping(value = "/getphoto",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPhoto() throws IOException{
        File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
        System.out.println(jarDir.getAbsolutePath());
        InputStream in = getClass()
                .getResourceAsStream("/static/assets/img/1/kara2.png");
        return IOUtils.toByteArray(in);
    }

}
