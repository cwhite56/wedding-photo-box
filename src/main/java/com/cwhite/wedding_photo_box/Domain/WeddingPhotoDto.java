package com.cwhite.wedding_photo_box.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeddingPhotoDto {
    private Long id;

    private String sentBy;

    private String filePath;
}
