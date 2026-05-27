package com.cwhite.wedding_photo_box.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cwhite.wedding_photo_box.Domain.WeddingPhotoDto;
import com.cwhite.wedding_photo_box.Domain.WeddingPhotoEntity;

@Component
public class PhotoMapper implements Mapper<WeddingPhotoEntity, WeddingPhotoDto>{

    private ModelMapper modelMapper;

    public PhotoMapper(ModelMapper modelMapper) {
        modelMapper.typeMap(WeddingPhotoDto.class, WeddingPhotoEntity.class)
            .addMappings(mapper -> mapper.skip(WeddingPhotoEntity::setPhotoData));
        this.modelMapper = modelMapper;
    }

    @Override
    public WeddingPhotoDto mapTo(WeddingPhotoEntity a) {
        return modelMapper.map(a, WeddingPhotoDto.class);
    }

    @Override
    public WeddingPhotoEntity mapFrom(WeddingPhotoDto b) {
        return modelMapper.map(b, WeddingPhotoEntity.class);
    }
    
}
