package dev.eliezer.lojaonline.modules.product.dtos;

import dev.eliezer.lojaonline.modules.image.entities.ImageEntity;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageLinkDTO {
        private Long id;
        private String filename;
        private String link;

        public void setLink(String link) {
        }

        public String getLink() {
                return "http://localhost:8080/images/" + this.id ;
        }



        public static ImageLinkDTO parseImagesLinkDTO(ImageEntity imageEntity){
                ImageLinkDTO imageLinkDTO = ImageLinkDTO.builder()
                                .id(imageEntity.getId())
                                .filename(imageEntity.getFilename())
                                .build();

                return imageLinkDTO;
        }
}
