package com.cwhite.wedding_photo_box;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoController {

    private PhotoService photoService;
    private PhotoMapper photoMapper;

    public PhotoController(PhotoService photoService, PhotoMapper photoMapper) {
        this.photoService = photoService;
        this.photoMapper = photoMapper;
    }
    
    @PostMapping(path = "/photos")
    public ResponseEntity<WeddingPhotoDto> createPhoto() {

    }
}
