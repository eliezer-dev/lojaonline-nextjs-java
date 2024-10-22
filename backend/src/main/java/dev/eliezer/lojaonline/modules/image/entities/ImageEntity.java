package dev.eliezer.lojaonline.modules.image.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.utils.ImageUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@Entity(name = "tb_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "9",
            requiredMode = Schema.RequiredMode.REQUIRED, description = "id of image")
    private Long id;

    @NotBlank(message = "[file] is not provided.")
    @Schema(example = "fotoJose.jpg", requiredMode = Schema.RequiredMode.REQUIRED, description = "original filename of image")
    private String filename;

    @NotBlank(message = "[type] is not provided.")
    @Schema(example = "", requiredMode = Schema.RequiredMode.REQUIRED, description = "type of image")
    private String imageType;

    @Lob
    @NotNull
    @Column(name = "imagedata", length = 1000, nullable = false)
    private byte[] imageData;

    @CreationTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "creation time of image")
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductEntity product;

    public String getImageData() {
        return Base64.getEncoder().encodeToString(ImageUtil.decompressImage(imageData));
    }

    @Transactional
    public void setImageData(@NotNull byte[] bytes) {
        this.imageData = ImageUtil.compressImage(bytes);
    }

}
