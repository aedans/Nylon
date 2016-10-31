package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

import java.util.Iterator;

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
        if (this.value % 1 == 0)
            return String.valueOf((long) this.value);
        else
            return String.valueOf(this.value);
    }

    @Override
    public NylonDouble clone() {
        return new NylonDouble(value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public Iterator<NylonObject> iterator(NylonStack nylonStack) throws NylonRuntimeException {
        return new Iterator<NylonObject>() {
            double i = 0;

            @Override
            public boolean hasNext() {
                return i < value;
            }

            @Override
            public NylonDouble next() {
                return new NylonDouble(i++);
            }
        };
    }

    @Override
    public Iterator<NylonObject> reverseIterator(NylonStack nylonStack) throws NylonRuntimeException {
        return new Iterator<NylonObject>() {
            double i = value;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public NylonDouble next() {
                return new NylonDouble(--i);
            }
        };
    }
}
