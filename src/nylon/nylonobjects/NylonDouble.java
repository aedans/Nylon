package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class NylonDouble implements NylonObject {
    private double d;

    public NylonDouble(double d) {
        this.d = d;
    }

    @Override
    public boolean toBoolean(NylonStack stack) {
        return d != 0;
    }

    @Override
    public double toDouble(NylonStack stack) {
        return d;
    }

    @Override
    public Iterator<NylonObject> toIterator(NylonStack stack) {
        return new Iterator<NylonObject>() {
            double i = 0;

            @Override
            public boolean hasNext() {
                return i < d;
            }

            @Override
            public NylonObject next() {
                return new NylonDouble(i++);
            }
        };
    }

    @Override
    public NylonStack toStack(NylonStack stack) {
        return new NylonStack(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NylonDouble && ((NylonDouble) obj).d == d;
    }

    @Override
    public String toString() {
        return String.valueOf(d);
    }
}
