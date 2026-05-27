package com.cwhite.wedding_photo_box.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.cwhite.wedding_photo_box.Domain.WeddingPhotoEntity;

public interface PhotoService {
    
    public WeddingPhotoEntity save(WeddingPhotoEntity weddingPhotoEntity, MultipartFile multipartFile) throws IOException;


    public Optional<WeddingPhotoEntity> findOne(Long id);

     public void delete(Long id);


     public List<WeddingPhotoEntity> findAll();
}
