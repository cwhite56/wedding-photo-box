package com.cwhite.wedding_photo_box;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoServiceImpl implements PhotoService{

    private PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public WeddingPhotoEntity save(WeddingPhotoEntity weddingPhotoEntity, MultipartFile multipartFile) throws IOException {

        if (multipartFile != null && !multipartFile.isEmpty()) {
        weddingPhotoEntity.setPhotoData(multipartFile.getBytes());
        }
        
        return photoRepository.save(weddingPhotoEntity);
    }
    
}
