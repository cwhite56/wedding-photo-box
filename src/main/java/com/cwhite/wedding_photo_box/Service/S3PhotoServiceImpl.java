package com.cwhite.wedding_photo_box.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cwhite.wedding_photo_box.Domain.WeddingPhotoEntity;
import com.cwhite.wedding_photo_box.Repository.PhotoRepository;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
@Profile("prod")
public class S3PhotoServiceImpl implements PhotoService{

    private PhotoRepository photoRepository;
    private S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3PhotoServiceImpl(PhotoRepository photoRepository, S3Client s3Client) {
        this.photoRepository = photoRepository;
        this.s3Client = s3Client;
    }

    @Override
    public WeddingPhotoEntity save(WeddingPhotoEntity weddingPhotoEntity, MultipartFile multipartFile) throws S3Exception, AwsServiceException, SdkClientException, IOException {
          
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String key = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

           PutObjectRequest request = PutObjectRequest.builder()
                .key(key)
                .bucket(bucketName)
                .build();

           s3Client.putObject(request, RequestBody.fromBytes(multipartFile.getBytes()));

            weddingPhotoEntity.setFilePath(key);
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
