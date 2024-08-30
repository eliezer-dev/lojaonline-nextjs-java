package dev.eliezer.lojaonline.modules.user.useCases;

import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.image.entities.ImageEntity;
import dev.eliezer.lojaonline.modules.image.repositories.ImageRepository;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import dev.eliezer.lojaonline.providers.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class InsertUserImageUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    public String execute (Long id, MultipartFile file) throws IOException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        Optional<ImageEntity> imageFound = Optional.empty();

        if (userEntity.getIdPicture() != null) {
            imageFound = imageRepository.findById(userEntity.getIdPicture());
        }

        ImageEntity imageSaved = imageRepository.save(
                ImageEntity.builder()
                        .imageType(file.getContentType())
                        .filename(file.getOriginalFilename())
                        .imageData(ImageUtil.compressImage(file.getBytes()))
                        .build()
        );
        userEntity.setIdPicture(imageSaved.getId());
        userRepository.save(userEntity);

        imageFound.ifPresent(imageEntity -> imageRepository.delete(imageEntity));

        return Base64.getEncoder().encodeToString(file.getBytes());

    }
}
