package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

interface Cloneable<T> {

    T clone() throws NylonRuntimeException;

}
