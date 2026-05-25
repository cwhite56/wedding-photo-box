package com.cwhite.wedding_photo_box;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class PhotoRepositoryTest {
    
   
    private PhotoRepository underTest;

    @Autowired
    public PhotoRepositoryTest(PhotoRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void TestThatPhotoCanBeCreatedAndRecalled() throws IOException {
        WeddingPhotoEntity testPhoto = 
            WeddingPhotoEntity.builder()
            .sentBy("Cameron")
            .photoData(MockPhotoUtil.createMockPhoto())
            .build();
        
        underTest.save(testPhoto);
        Optional<WeddingPhotoEntity> result = underTest.findById(testPhoto.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testPhoto);
    }

    @Test
    public void TestThatManyPhotosCanBeCreatedAndRecalled() throws IOException {
        WeddingPhotoEntity testPhotoA = 
            WeddingPhotoEntity.builder()
            .sentBy("Cameron")
            .photoData(MockPhotoUtil.createMockPhoto())
            .build();
        underTest.save(testPhotoA);

        WeddingPhotoEntity testPhotoB = 
            WeddingPhotoEntity.builder()
            .sentBy("Carli")
            .photoData(MockPhotoUtil.createMockPhoto())
            .build();
        underTest.save(testPhotoB);

        WeddingPhotoEntity testPhotoC = 
            WeddingPhotoEntity.builder()
            .sentBy("Machael")
            .photoData(MockPhotoUtil.createMockPhoto())
            .build();
        underTest.save(testPhotoC);

        Iterable<WeddingPhotoEntity> result = underTest.findAll();
        
        assertThat(result).hasSize(3).containsExactly(testPhotoA, testPhotoB, testPhotoC);
    }

    @Test
    public void TestThatPhotoCanDeleted() throws IOException {
        WeddingPhotoEntity testPhoto = 
            WeddingPhotoEntity.builder()
            .sentBy("Cameron")
            .photoData(MockPhotoUtil.createMockPhoto())
            .build();
        
        underTest.save(testPhoto);
        underTest.deleteById(testPhoto.getId());
        Optional<WeddingPhotoEntity> result = underTest.findById(testPhoto.getId());
        
        assertThat(result).isEmpty();
        
    }
}
