package com.cwhite.wedding_photo_box.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "photos")
public class WeddingPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wedding_photo_id_seq")
    @SequenceGenerator(name = "wedding_photo_id_seq", sequenceName = "wedding_photo_id_seq", allocationSize = 1)
    private Long id;

    private String sentBy;

    //@Lob
    @Column(columnDefinition = "bytea")
    private byte[] photoData;
}
