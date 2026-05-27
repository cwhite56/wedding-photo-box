package com.cwhite.wedding_photo_box.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cwhite.wedding_photo_box.Domain.WeddingPhotoEntity;
import com.cwhite.wedding_photo_box.Repository.PhotoRepository;

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

    @Override
    public Optional<WeddingPhotoEntity> findOne(Long id) {
        return photoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        photoRepository.deleteById(id);
    }

    @Override
    public List<WeddingPhotoEntity> findAll() {
        return StreamSupport.stream(photoRepository
        .findAll()
        .spliterator(), false)
        .collect(Collectors.toList());
    }
    
}
