package dev.eliezer.lojaonline.modules.image.mappers;

import dev.eliezer.lojaonline.modules.image.dtos.ImageLinkDTO;
import dev.eliezer.lojaonline.modules.image.entities.ImageEntity;

public class ImageMapper {

    public static ImageLinkDTO toImageLinkDTO(ImageEntity imageEntity) {
        return ImageLinkDTO.builder()
                .id(imageEntity.getId())
                .build();
    }

}
