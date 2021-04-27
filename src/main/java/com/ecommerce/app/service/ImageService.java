package com.ecommerce.app.service;

import com.ecommerce.app.domain.Image;
import com.ecommerce.app.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {
	private final ImageRepository imageRepository;
	public void saveImage(Image image){
		imageRepository.save(image);
	}
	public Iterable<Image> getImages(){
			return imageRepository.findAll();
	}
	
	public void deleteImage(Long id){
		imageRepository.deleteById(id);
	}
}
