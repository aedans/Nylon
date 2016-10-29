package nylon.exceptions;

import nylon.objects.NylonObject;

/**
 * Created by Aedan Smith.
 */

public class InvalidActionException extends NylonRuntimeException {

    public InvalidActionException(String action, NylonObject object) {
        super("(" + object.getClass().getSimpleName() + ") Could not " + action + " " + object.toString());
    }

}
