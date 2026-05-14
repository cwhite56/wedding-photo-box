package com.cwhite.wedding_photo_box;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PhotoMapper implements Mapper<WeddingPhotoEntity, WeddingPhotoDto>{

    private ModelMapper modelMapper;

    public PhotoMapper(ModelMapper modelMapper) {
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
