package com.cwhite.wedding_photo_box.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cwhite.wedding_photo_box.Domain.WeddingPhotoEntity;

@Repository
public interface PhotoRepository extends CrudRepository<WeddingPhotoEntity, Long>{
}
