package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonDouble extends NylonObject {
    private double d;

    public NylonDouble(double d) {
        this.d = d;
    }

    @Override
    public boolean toBoolean(Stack<NylonObject> stack) {
        return d != 0;
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return d;
    }

    @Override
    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
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
    public NylonList toList(Stack<NylonObject> stack) {
        return new NylonList(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NylonDouble && ((NylonDouble) obj).d == d;
    }

    @Override
    public String toString() {
        return String.valueOf(d);
    }

    @Override
    public NylonObject concatenate(NylonObject object, Stack<NylonObject> stack) {
        d += object.toDouble(stack);
        return this;
    }

    @Override
    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) {
        d -= object.toDouble(stack);
        return this;
    }
}
