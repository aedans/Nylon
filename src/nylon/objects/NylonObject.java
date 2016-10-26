package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public interface NylonObject extends Cloneable<NylonObject> {

    @Override
    String toString();

    int toInteger() throws NylonRuntimeException;

    double toDouble() throws NylonRuntimeException;

    boolean toBoolean() throws NylonRuntimeException;

    NylonObject increment() throws NylonRuntimeException;

    NylonObject decrement() throws NylonRuntimeException;

    NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException;

}
