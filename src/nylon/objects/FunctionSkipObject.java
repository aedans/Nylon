package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public class FunctionSkipObject implements NylonObject {

    @Override
    public String toString() {
        return "";
    }

    @Override
    public long toLong() {
        return 0;
    }

    @Override
    public boolean toBoolean() {
        return false;
    }

    @Override
    public NylonObject increment() throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not increment FunctionSkipObject object.");
    }

    @Override
    public NylonObject decrement() throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not decrement FunctionSkipObject object.");
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        throw new NylonRuntimeException("Could not concatenate FunctionSkipObject object.");
    }

    @Override
    public NylonObject clone() {
        return new FunctionSkipObject();
    }

}
