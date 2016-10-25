package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public class FunctionSkip implements NylonObject {

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int toInteger() {
        return 0;
    }

    @Override
    public boolean toBoolean() {
        return false;
    }

    @Override
    public NylonObject increment() throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not increment FunctionSkip object.");
    }

    @Override
    public NylonObject decrement() throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not decrement FunctionSkip object.");
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not concatenate FunctionSkip object.");
    }

}
