package nylon.objects;

import nylon.exceptions.InvalidActionException;
import nylon.exceptions.NylonRuntimeException;
import nylon.exceptions.UnconvertableTypeException;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonStack extends Stack<NylonObject> implements NylonObject {

    @Override
    public char toChar() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Character.class);
    }

    @Override
    public int toInteger() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Integer.class);
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Double.class);
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Boolean.class);
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
    public NylonStack concatenate(NylonObject nylonObject) {
        if (nylonObject instanceof NylonStack) {
            ((NylonStack) nylonObject).addAll(this);
            ((NylonStack) nylonObject).clear();
        } else {
            this.add(nylonObject);
        }
        return this;
    }

    @SuppressWarnings("Convert2streamapi")
    @Override
    public NylonStack clone() {
        NylonStack nylonStack = new NylonStack();
        for (NylonObject object : this) {
            nylonStack.add(object.clone());
        }
        return nylonStack;
    }

    public NylonObject[] pop(int i) {
        NylonObject[] objects = new NylonObject[i];
        for (i--; i >= 0; i--) {
            objects[i] = pop();
        }
        return objects;
    }

}
