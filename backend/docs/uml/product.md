```mermaid

classDiagram
class ProductEntity {
+Long id
+String name
+String description
+Double price
+Integer stockQuantity
+String barcode
+LocalDateTIme createdAt
+LocalDateTIme updatedAt
+Boolean active
+Double promotionalPrice
+LocalDateTIme promotionalDateStart
+LocalDateTIme promotionalDateEnd
+String sku
+create()
+update()
+delete()
+find()
}

    class CategoryEntity {
        +Long id
        +String name
        +String description
        +LocalDateTImeTime createdAt
        +LocalDateTImeTime updatedAt
        +Long parent
        +create()
        +update()
        +delete()
        +find()
    }

    class ImageEntity {
        +Long id
        +String url
        +String altText
        +LocalDateTIme createdAt
        +LocalDateTIme updatedAt
        +create()
        +update()
        +delete()
        +find()
    }

    CategoryEntity "0..1" <--> "0..*" ProductEntity : contém
    ProductEntity "1" --> "0..*" ImageEntity : possui

```