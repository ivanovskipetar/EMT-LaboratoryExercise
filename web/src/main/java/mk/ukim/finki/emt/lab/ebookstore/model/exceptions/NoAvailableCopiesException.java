package mk.ukim.finki.emt.lab.ebookstore.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoAvailableCopiesException extends RuntimeException{
    public NoAvailableCopiesException(Long id) {
        super(String.format("No available copies of book with id %d",id));
    }
}
