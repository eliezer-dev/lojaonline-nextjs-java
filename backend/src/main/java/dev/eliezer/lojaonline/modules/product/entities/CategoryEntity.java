package dev.eliezer.lojaonline.modules.product.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "tb_category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "id of category")
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Schema(example = "Hortifruti", requiredMode = Schema.RequiredMode.REQUIRED, description = "description of category")
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<CategoryEntity> subcategories = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    @CreationTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "creation time of category")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "update time of category")
    private LocalDateTime updateAt;


}
