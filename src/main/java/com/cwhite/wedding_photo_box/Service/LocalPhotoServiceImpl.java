package com.cwhite.wedding_photo_box.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cwhite.wedding_photo_box.Domain.WeddingPhotoEntity;
import com.cwhite.wedding_photo_box.Repository.PhotoRepository;

@Service
@Profile("local")
public class LocalPhotoServiceImpl implements PhotoService{

    private PhotoRepository photoRepository;

    @Value("${upload.directory}")
    private String uploadDirectory;

    public LocalPhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public WeddingPhotoEntity save(WeddingPhotoEntity weddingPhotoEntity, MultipartFile multipartFile) throws IOException {

        
                        
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String filename = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
            String destination = uploadDirectory + filename;

            weddingPhotoEntity.setFilePath(destination);
            multipartFile.transferTo(new File(destination));
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
