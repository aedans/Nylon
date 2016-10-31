package nylon.objects;

import nylon.exceptions.InvalidActionException;
import nylon.exceptions.NylonRuntimeException;
import nylon.exceptions.UnconvertableTypeException;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class FunctionSkipObject implements NylonObject {

    @Override
    public String toString() {
        return "";
    }

    @Override
    public char toChar() throws NylonRuntimeException {
        return '!';
    }

    @Override
    public int toInteger() {
        return 0;
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        return 0;
    }

    @Override
    public boolean toBoolean() {
        return false;
    }

    @Override
    public NylonString increment() throws NylonRuntimeException {
        throw new InvalidActionException("increment", this);
    }

    @Override
    public NylonString decrement() throws NylonRuntimeException {
        throw new InvalidActionException("decrement", this);
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        throw new InvalidActionException("concatenate", this);
    }

    @Override
    public FunctionSkipObject clone() {
        return new FunctionSkipObject();
    }

    @Override
    public Iterator<NylonObject> iterator(NylonStack nylonStack) throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Iterator.class);
    }

    @Override
    public Iterator<NylonObject> reverseIterator(NylonStack nylonStack) throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Iterator.class);
    }
}
