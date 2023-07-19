package application.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNorFoundException extends RuntimeException {
    private static final Long serialVersion = 1L;

    public ResourceNorFoundException(String ex) {
        super(ex);
    }


}