package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonStack extends Stack<NylonObject> implements NylonObject {

    @Override
    public int toInteger() throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not convert NylonStack to integer.");
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not convert NylonStack to double.");
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not convert NylonStack to boolean.");
    }

    @Override
    public NylonObject increment() throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not increment NylonStack.");
    }

    @Override
    public NylonObject decrement() throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not decrement NylonStack.");
    }

    @Override
    public NylonStack concatenate(NylonObject nylonObject) {
        if (nylonObject instanceof NylonStack){
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

}
