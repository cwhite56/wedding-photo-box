package com.cwhite.wedding_photo_box.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeGeneratorService {

    private String port = "8080";

    public void generation() throws WriterException, IOException {
        String localHost = getRealLocalIP();
        String url = "http://" + localHost + ":" + port;
        System.out.println("QR Code URL: " + url);
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage QRcode = MatrixToImageWriter.toBufferedImage(matrix);
        File outputFile = new File("QRcode.png");
        ImageIO.write(QRcode, "png", outputFile);

    }

    public void generation(String url) throws WriterException, IOException {
        System.out.println("QR Code URL: " + url);
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage QRcode = MatrixToImageWriter.toBufferedImage(matrix);
        File outputFile = new File("QRcode.png");
        ImageIO.write(QRcode, "png", outputFile);
    }

    private String getRealLocalIP() throws IOException {
        try (Socket socket = new Socket()) {
            // connect to any external address — doesn't actually send data
            socket.connect(new InetSocketAddress("1.1.1.1", 80));
            return socket.getLocalAddress().getHostAddress();
    }
}

}
