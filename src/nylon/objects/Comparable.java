package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public interface Comparable<A, T> {

    int compareTo(T t, A a) throws NylonRuntimeException;

}
