package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class NylonDouble extends NylonObject<Double> {
    public NylonDouble(double d) {
        super(d, Type.DOUBLE, "Double");
    }

    @Override
    public double toDouble() {
        return this.value;
    }

    @Override
    public Iterator<NylonObject> toIterator() throws NylonException {
        return new Iterator<NylonObject>() {
            double l = 0;

            @Override
            public boolean hasNext() {
                return l < value;
            }

            @Override
            public NylonObject next() {
                return new NylonDouble(l++);
            }
        };
    }

    @Override
    public NylonDouble concatenate(NylonObject object) throws NylonException {
        this.value += object.toDouble();
        return this;
    }

    @Override
    public NylonDouble subtract(NylonObject object) throws NylonException {
        this.value -= object.toDouble();
        return this;
    }

    @Override
    public NylonDouble multiply(NylonObject object) throws NylonException {
        this.value *= object.toDouble();
        return this;
    }

    @Override
    public NylonDouble divide(NylonObject object) throws NylonException {
        this.value /= object.toDouble();
        return this;
    }

    @Override
    public NylonDouble clone() {
        return new NylonDouble(this.value);
    }
}
