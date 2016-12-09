package nylon;

/**
 * Created by Aedan Smith.
 */

public class NylonException extends RuntimeException {
    public NylonException(String message) {
        super(message);
    }

    public NylonException(String message, Object src) {
        this(message + " (" + src.getClass().getSimpleName() + ")");
    }

    public NylonException(String message, int character) {
        this(message + " (char " + character + ")");
    }

    public NylonException(String message, Object src, int character) {
        this(message + " (" + src.getClass().getSimpleName() + ", char " + character + ")");
    }
}
