package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

interface Iterable<A, T> {

    Iterator<T> iterator(A a) throws NylonRuntimeException;

    Iterator<T> reverseIterator(A a) throws NylonRuntimeException;

}
