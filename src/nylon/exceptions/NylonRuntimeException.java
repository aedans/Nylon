package nylon.exceptions;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntimeException extends Exception {

    public NylonRuntimeException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "NylonRuntimeException: " + getMessage();
    }

}
