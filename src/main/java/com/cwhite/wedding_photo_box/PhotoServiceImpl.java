package com.cwhite.wedding_photo_box;

import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService{

    private PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public WeddingPhotoEntity save(WeddingPhotoEntity weddingPhotoEntity) {
        return photoRepository.save(weddingPhotoEntity);
    }
    
}
