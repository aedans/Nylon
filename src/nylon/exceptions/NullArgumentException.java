package nylon.exceptions;

import nylon.objects.NylonFunction;

/**
 * Created by Aedan Smith.
 */

public class NullArgumentException extends NylonRuntimeException {

    public NullArgumentException(NylonFunction nylonFunction) {
        super("(" + nylonFunction + ") Could not parse null arguments");
    }

}
