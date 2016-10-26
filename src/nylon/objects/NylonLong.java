package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public class NylonLong implements NylonObject {

    private long value;

    public NylonLong(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public long toLong() {
        return this.value;
    }

    @Override
    public boolean toBoolean() {
        return this.value != 0;
    }

    @Override
    public NylonLong increment() {
        this.value++;
        return this;
    }

    @Override
    public NylonLong decrement() {
        this.value--;
        return this;
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        return new NylonLong(this.value + nylonObject.toLong());
    }

    @Override
    public NylonObject clone() {
        return new NylonLong(value);
    }

}
