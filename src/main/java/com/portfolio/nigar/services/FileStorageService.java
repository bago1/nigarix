package com.portfolio.nigar.services;

import java.io.IOException;
import java.util.stream.Stream;

import com.portfolio.nigar.entities.FileDB;
import com.portfolio.nigar.repos.FileDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {

    @Autowired
    private FileDBRepo fileDBRepo;

    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return fileDBRepo.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepo.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepo.findAll().stream();
    }
}