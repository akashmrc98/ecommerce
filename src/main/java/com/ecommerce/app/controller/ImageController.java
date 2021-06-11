package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Image;
import com.ecommerce.app.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce/images")
public class ImageController {

	private final ImageService imageService;

	@GetMapping
	public ResponseEntity<Iterable<Image>> getImages() {
		Iterable<Image> images = imageService.getImages();
		return new ResponseEntity<Iterable<Image>>(images, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Image> uploadImage(@RequestParam("image") MultipartFile file)
	throws IOException {
		String fileName =
		StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

		Image img = new Image();
		img.setName(fileName);
		img.setSize(file.getSize());
		img.setContent(file.getBytes());
		img.setUploadedAt(new Date());

		imageService.saveImage(img);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("product_id", img.getId().toString());

		return new ResponseEntity<Image>(img, responseHeaders, HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteImage(@Param("id") Long id) {
		imageService.deleteImage(id);
	}
}