package com.cwhite.wedding_photo_box;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoController {

    private PhotoService photoService;
    private PhotoMapper photoMapper;

    public PhotoController(PhotoService photoService, PhotoMapper photoMapper) {
        this.photoService = photoService;
        this.photoMapper = photoMapper;
    }
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
    public ResponseEntity<WeddingPhotoDto> uploadPhoto(@RequestPart("data") WeddingPhotoDto weddingPhotoDto, @RequestPart("file")MultipartFile multipartFile) throws IOException {
        
        WeddingPhotoEntity weddingPhotoEntity = photoMapper.mapFrom(weddingPhotoDto);
        WeddingPhotoEntity savedPhoto = photoService.save(weddingPhotoEntity, multipartFile);

        return new ResponseEntity<>(photoMapper.mapTo(savedPhoto), HttpStatus.CREATED);

    }
}
