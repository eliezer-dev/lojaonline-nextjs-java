package dev.eliezer.lojaonline.modules.product.useCases;

import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.image.entities.ImageEntity;
import dev.eliezer.lojaonline.modules.image.repositories.ImageRepository;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.repositories.ProductRepository;
import dev.eliezer.lojaonline.providers.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UploadProductImageUseCase {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    public ProductEntity execute (MultipartFile file, Long productId) throws IOException {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(productId));

//        Optional<ImageEntity> imageFound = Optional.empty();
//
//        if (userEntity.getIdImage() != null) {
//            imageFound = imageRepository.findById(userEntity.getIdImage());
//
//        }

        ImageEntity imageSaved = imageRepository.save(
                ImageEntity.builder()
                        .imageType(file.getContentType())
                        .filename(file.getOriginalFilename())
                        .imageData(ImageUtil.compressImage(file.getBytes()))
                        .product(productEntity)
                        .build()
        );

        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException(productId));
//        userEntity.setIdImage(imageSaved.getId());
//
//        UserResponseDTO userUpdated =  UserResponseDTO.parseUserResponseDTO(userRepository.save(userEntity));
//
//        imageFound.ifPresent(imageEntity ->
//                imageRepository.delete(imageEntity)
//
//        );

    }
}
