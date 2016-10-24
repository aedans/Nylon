package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public interface NylonObject {

    @Override
    String toString();

    int toInteger() throws NylonRuntimeException;

    NylonObject increment() throws NylonRuntimeException;

    NylonObject decrement() throws NylonRuntimeException;

    NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException;

}
