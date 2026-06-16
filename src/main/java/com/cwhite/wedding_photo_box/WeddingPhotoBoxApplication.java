package com.cwhite.wedding_photo_box;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cwhite.wedding_photo_box.Service.QRCodeGeneratorService;

@SpringBootApplication

public class WeddingPhotoBoxApplication implements CommandLineRunner{

	private QRCodeGeneratorService qrCodeGeneratorService;

	@Value("${app.url}")
	String url;


	public WeddingPhotoBoxApplication(QRCodeGeneratorService qrCodeGeneratorService) {
		this.qrCodeGeneratorService = qrCodeGeneratorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(WeddingPhotoBoxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		qrCodeGeneratorService.generation(url);
		

	}

}
