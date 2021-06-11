package com.portfolio.nigar.controllers;

import com.portfolio.nigar.entities.UploadFile;
import com.portfolio.nigar.repos.EmailRepo;
import com.portfolio.nigar.repos.UploadFileRepo;
import com.portfolio.nigar.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
        String uploadDir ="user-photos/"+ savedFile.getId();
        FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
        return new RedirectView("/",true);
    }

    @PostMapping("/a")
    public String ss(){
        return "/";
    }

}
