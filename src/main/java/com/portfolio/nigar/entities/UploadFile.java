package com.portfolio.nigar.entities;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UploadFile {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = true, length = 64)
    private String photos;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PhotoType photoType;



    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/static/assets/img/" + id + "/" + photos;
    }
}
