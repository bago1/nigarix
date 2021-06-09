package com.portfolio.nigar.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
@Data
@Component
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    // 1 personal, 2 portfolio, 3 article_photos
    @Column(nullable = true, length = 64)
    private String photos;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/user-photos/" + id + "/" + photos;
    }

}
