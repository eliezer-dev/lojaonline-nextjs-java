package dev.eliezer.lojaonline.modules.image.useCases;

import dev.eliezer.lojaonline.exceptions.BusinessException;
import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.image.entities.ImageEntity;
import dev.eliezer.lojaonline.modules.image.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class GetImageUseCase {

    @Autowired
    private ImageRepository imageRepository;

    public ImageEntity execute (Long id) {

        ImageEntity imageFound = imageRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (imageFound.getImageData() == null || imageFound.getImageData().toString().isEmpty()) {
            throw new BusinessException("The image is corrupted.");
        }

        return imageFound;

    }
}
