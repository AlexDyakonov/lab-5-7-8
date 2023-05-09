package ru.home.app.server.exception;

/**
 * The type File exception.
 */
public class FileException extends RuntimeException {
    /**
     * Instantiates a new File exception.
     *
     * @param message the message
     */
    public FileException(String message) {
        super(message);
    }

    /**
     * Instantiates a new File exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
