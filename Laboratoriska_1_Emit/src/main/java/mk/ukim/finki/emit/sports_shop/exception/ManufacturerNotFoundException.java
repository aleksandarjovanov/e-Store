package mk.ukim.finki.emit.sports_shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Manufacturer Not Found Exception")
public class ManufacturerNotFoundException extends RuntimeException {
}
