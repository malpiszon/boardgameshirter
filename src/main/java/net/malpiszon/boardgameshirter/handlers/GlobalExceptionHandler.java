package net.malpiszon.boardgameshirter.handlers;

import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.exceptions.NoSuchEntityException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = NullPointerException.class)
    public String handleNullPointerExceptionException(NullPointerException e) {
        return "";
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = EntityAlreadyExistsException.class)
    public String handleEntityAlreadyExistsException(Exception e) {
        LOG.info(e.getMessage());
        return "";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoSuchEntityException.class)
    public String handleNoSuchEntityException(Exception e) {
        LOG.info(e.getMessage());
        return "";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public String handleAccessDeniedException(Exception e) {
        LOG.info(e.getMessage());
        return "";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e) {
        LOG.info("Bad request: " + e.getMessage());
        return "";
    }
}
