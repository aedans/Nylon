package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public class NylonString implements NylonObject {

    private String value;

    public NylonString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int toInteger() throws NylonRuntimeException {
        try {
            return Integer.parseInt(value);
        } catch (Exception e){
            throw new NylonRuntimeException("Could not convert string to integer (string \"" + value + "\"");
        }
    }

    @Override
    public NylonString increment() throws NylonRuntimeException {
        throw new NylonRuntimeException("Cannot increment a string.");
    }

    @Override
    public NylonString decrement() throws NylonRuntimeException {
        throw new NylonRuntimeException("Cannot increment a string.");
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        return new NylonString(this.value + nylonObject.toString());
    }

}
