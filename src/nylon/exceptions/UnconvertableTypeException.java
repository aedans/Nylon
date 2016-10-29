package nylon.exceptions;

import nylon.objects.NylonObject;

/**
 * Created by Aedan Smith.
 */

public class UnconvertableTypeException extends NylonRuntimeException {

    public UnconvertableTypeException(NylonObject object, Class type) {
        super("Cannot convert " + object.getClass().getSimpleName() + " \"" + object + "\" to " + type.getSimpleName());
    }

}
