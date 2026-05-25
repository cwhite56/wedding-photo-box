package com.cwhite.wedding_photo_box;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MockPhotoUtil {

    public static byte[] createMockPhoto() throws IOException{
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "png", outputStream);

        return outputStream.toByteArray();
    }
    
}
