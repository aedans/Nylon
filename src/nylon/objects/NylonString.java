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
    public long toLong() throws NylonRuntimeException {
        try {
            return Integer.parseInt(value);
        } catch (Exception e){
            throw new NylonRuntimeException("Could not convert string object to integer (string \"" + value + "\"");
        }
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        if (value.equals("t") || value.equals("true"))
            return true;
        if (value.equals("f") || value.equals("false"))
            return false;
        throw new NylonRuntimeException("Could not convert string object to boolean (string \"" + value + "\"");
    }

    @Override
    public NylonString increment() throws NylonRuntimeException {
        throw new NylonRuntimeException("Cannot increment a string object.");
    }

    @Override
    public NylonString decrement() throws NylonRuntimeException {
        throw new NylonRuntimeException("Cannot increment a string object.");
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) {
        return new NylonString(this.value + nylonObject.toString());
    }

}
