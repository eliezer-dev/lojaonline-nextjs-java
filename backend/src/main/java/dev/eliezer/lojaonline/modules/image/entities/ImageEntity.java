package dev.eliezer.lojaonline.modules.image.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;

    @CreationTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "creation time of image")
    private LocalDateTime createAt;

}
