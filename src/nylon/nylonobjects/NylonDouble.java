package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonDouble extends NylonObject<Double> {
    public NylonDouble(double d) {
        super(d, Type.DOUBLE);
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return this.value;
    }

    @Override
    public NylonDouble concatenate(NylonObject object, Stack<NylonObject> stack) {
        this.value += object.toDouble(stack);
        return this;
    }

    @Override
    public NylonDouble subtract(NylonObject object, Stack<NylonObject> stack) {
        this.value -= object.toDouble(stack);
        return this;
    }

    @Override
    public NylonDouble multiply(NylonObject object, Stack<NylonObject> stack) {
        this.value *= object.toDouble(stack);
        return this;
    }

    @Override
    public NylonDouble divide(NylonObject object, Stack<NylonObject> stack) {
        this.value /= object.toDouble(stack);
        return this;
    }

    @Override
    public NylonDouble clone() {
        return new NylonDouble(this.value);
    }
}
