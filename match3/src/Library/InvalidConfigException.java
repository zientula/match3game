package Library;

/**
 * Exception that is thrown when config is invalid
 */
public class InvalidConfigException extends RuntimeException {
    public InvalidConfigException(String message) {
        super(message);
    }
}
