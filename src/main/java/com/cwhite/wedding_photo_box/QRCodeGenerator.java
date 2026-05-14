package com.cwhite.wedding_photo_box;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

    public static void generation(String url) throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage QRcode = MatrixToImageWriter.toBufferedImage(matrix);
        File outputFile = new File("QRcode.png");
        ImageIO.write(QRcode, "png", outputFile);

    }
    public static void main(String[] args) throws WriterException, IOException {
		
		String url = "https://github.com/cwhite56";
		QRCodeGenerator.generation(url);

	}
}
