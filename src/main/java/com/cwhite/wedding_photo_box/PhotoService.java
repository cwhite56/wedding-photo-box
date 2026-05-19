package com.cwhite.wedding_photo_box;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    
    public WeddingPhotoEntity save(WeddingPhotoEntity weddingPhotoEntity, MultipartFile multipartFile) throws IOException;
}
