package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonLong extends NylonObject<Long> {
    public NylonLong(long l) {
        super(l, Type.LONG);
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return this.value;
    }

    @Override
    public long toLong(Stack<NylonObject> stack) {
        return this.value;
    }

    @Override
    public NylonLong concatenate(NylonObject object, Stack<NylonObject> stack) {
        this.value += object.toLong(stack);
        return this;
    }

    @Override
    public NylonLong subtract(NylonObject object, Stack<NylonObject> stack) {
        this.value -= object.toLong(stack);
        return this;
    }

    @Override
    public NylonLong multiply(NylonObject object, Stack<NylonObject> stack) {
        this.value *= object.toLong(stack);
        return this;
    }

    @Override
    public NylonLong divide(NylonObject object, Stack<NylonObject> stack) {
        this.value /= object.toLong(stack);
        return this;
    }

    @Override
    public NylonLong clone() {
        return new NylonLong(this.value);
    }
}
