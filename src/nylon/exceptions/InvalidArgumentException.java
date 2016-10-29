package nylon.exceptions;

import nylon.objects.NylonFunction;

/**
 * Created by Aedan Smith.
 */

public class InvalidArgumentException extends NylonRuntimeException {

    public InvalidArgumentException(NylonFunction nylonFunction) {
        super("(" + nylonFunction + ") Invalid arguments.");
    }

}
