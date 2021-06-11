package com.portfolio.nigar.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath =  Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try
            (InputStream is = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(is,filePath,StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException IOE){
            throw new IOException("Could not save image file "+ fileName,IOE);
        }

    }
}
//save eden Files.copy(inputstream, fileapath) -dir. yani bu inputstreami, yani bytleari save ele bu patha.


