package eliezer.dev.lojavirtual.modules.product.controller;

import eliezer.dev.lojavirtual.modules.product.entities.ProductEntity;
import eliezer.dev.lojavirtual.modules.product.useCases.CreateProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsRestController {
    @Autowired
    private CreateProductUseCase createProductUseCase;

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("teste");
    }

    @PostMapping
    public ResponseEntity<Object> execute(@RequestBody ProductEntity product) {
        try {
            var result = createProductUseCase.execute(product);
            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
