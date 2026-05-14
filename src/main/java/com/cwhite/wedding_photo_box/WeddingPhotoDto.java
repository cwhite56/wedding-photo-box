package com.cwhite.wedding_photo_box;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeddingPhotoDto {
    Long id;

    String sentBy;

    byte[] photoData;
}
