package com.portfolio.nigar.entities;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
public class UploadFile {

    public enum PhotoType{
        PROFILE_PHOTO, OTHER_PHOTO
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = true, length = 64)
    private String photos;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UploadFile.PhotoType photoType;



    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/static/assets/img/" + id + "/" + photos;
    }
}
