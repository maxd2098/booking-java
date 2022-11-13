package plugin.atb.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> notFoundHandler(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessage(exception.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<Object> existenceHandler(ConflictException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseMessage(exception.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(value = {RangeNotSatisfiableException.class})
    public ResponseEntity<Object> rangeNotSatisfiableHandler(RangeNotSatisfiableException exception) {
        return ResponseEntity
                .status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
                .body(new ResponseMessage(exception.getMessage(), HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE));
    }
}
