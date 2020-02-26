package mk.ukim.finki.emit.sports_shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Product Not Found Exception")
public class ProductNotFoundException extends RuntimeException {
}
