package server.exception;

/**
 * The type server.Validation server.exception.
 */
public class ValidationException extends RuntimeException{
    /**
     * Instantiates a new server.Validation server.exception.
     *
     * @param message the message
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new server.Validation server.exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new server.Validation server.exception.
     *
     * @param cause the cause
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }
}