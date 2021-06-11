package com.portfolio.nigar.controllers;

import com.portfolio.nigar.entities.UploadFile;
import com.portfolio.nigar.repos.EmailRepo;
import com.portfolio.nigar.repos.UploadFileRepo;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final UploadFileRepo repo;
    private final String UPLOAD_DIR = "./src/main/resources/uploads/";


    @PostMapping("/upload")
    public ModelAndView addPhoto(@RequestParam("file") MultipartFile file, RedirectAttributes red){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }        return new ModelAndView("redirect:/");
    }
}
