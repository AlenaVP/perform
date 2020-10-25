package by.minsk.perform.exception;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 25 Oct, 2020
 */

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
