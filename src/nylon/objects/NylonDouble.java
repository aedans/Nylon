package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public class NylonDouble implements NylonObject {
    
    private double value;
    
    public NylonDouble(double value){
        this.value = value;
    }

    @Override
    public char toChar() {
        return (char) value;
    }

    @Override
    public int toInteger() {
        return (int) this.value;
    }

    @Override
    public double toDouble() {
        return this.value;
    }

    @Override
    public boolean toBoolean() {
        return this.value != 0;
    }

    @Override
    public NylonDouble increment() {
        this.value++;
        return this;
    }

    @Override
    public NylonDouble decrement() {
        this.value--;
        return this;
    }

    @Override
    public NylonDouble concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        return new NylonDouble(this.value + nylonObject.toDouble());
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public NylonDouble clone() {
        return new NylonDouble(value);
    }

    public double getValue() {
        return value;
    }

}
