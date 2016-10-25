package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public class NylonInteger implements NylonObject {

    private int value;

    public NylonInteger(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public int toInteger() {
        return this.value;
    }

    @Override
    public boolean toBoolean() {
        return this.value != 0;
    }

    @Override
    public NylonInteger increment() {
        this.value++;
        return this;
    }

    @Override
    public NylonInteger decrement() {
        this.value--;
        return this;
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        return new NylonInteger(this.value + nylonObject.toInteger());
    }

}
