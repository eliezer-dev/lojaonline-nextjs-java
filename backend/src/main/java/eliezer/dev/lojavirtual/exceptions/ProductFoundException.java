package eliezer.dev.lojavirtual.exceptions;

public class ProductFoundException extends RuntimeException{
    public ProductFoundException() {
        super("Product alright exists");
    }
}
