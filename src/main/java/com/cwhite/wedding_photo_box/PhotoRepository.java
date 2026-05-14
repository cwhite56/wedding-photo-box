package com.cwhite.wedding_photo_box;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends CrudRepository<WeddingPhotoEntity, Long>{
}
