package nylon.exceptions;

import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntimeException extends Exception {

    public NylonRuntimeException(String message){
        super(message);
    }

    public NylonRuntimeException(String message, LinkedList<NylonObject> stack){
        super(message + "\nActive stack: " + stack);
    }

    @Override
    public String toString() {
        return "NylonRuntimeException: " + getMessage();
    }
}
