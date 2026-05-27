package com.cwhite.wedding_photo_box.Controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cwhite.wedding_photo_box.Domain.WeddingPhotoDto;
import com.cwhite.wedding_photo_box.Domain.WeddingPhotoEntity;
import com.cwhite.wedding_photo_box.Mapper.PhotoMapper;
import com.cwhite.wedding_photo_box.Service.PhotoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private PhotoService photoService;
    private PhotoMapper photoMapper;

    public PhotoController(PhotoService photoService, PhotoMapper photoMapper) {
        this.photoService = photoService;
        this.photoMapper = photoMapper;
    }
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
    public ResponseEntity<WeddingPhotoDto> uploadPhoto(@RequestPart("data") WeddingPhotoDto weddingPhotoDto, @RequestPart("image")MultipartFile multipartFile) throws IOException {
        
        WeddingPhotoEntity weddingPhotoEntity = photoMapper.mapFrom(weddingPhotoDto);
        
        WeddingPhotoEntity savedPhoto = photoService.save(weddingPhotoEntity, multipartFile);

        return new ResponseEntity<>(photoMapper.mapTo(savedPhoto), HttpStatus.CREATED);

    }

    @GetMapping(value = "/read/{id}")
    public ResponseEntity<WeddingPhotoDto> getPhoto(@PathVariable("id") Long id) {
        Optional<WeddingPhotoEntity> foundPhoto = photoService.findOne(id);

        return foundPhoto.map(weddingPhotoEntity -> {
            WeddingPhotoDto weddingPhotoDto = photoMapper.mapTo(weddingPhotoEntity);
            return new ResponseEntity<>(weddingPhotoDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/read")
    public List<WeddingPhotoDto> getAllPhotos() {
        List<WeddingPhotoEntity> photos = photoService.findAll();
        return photos.stream()
                .map(photoMapper::mapTo)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deletePhoto(@PathVariable("id") Long id) {
        photoService.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
