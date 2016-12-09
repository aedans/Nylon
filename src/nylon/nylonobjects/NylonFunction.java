package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public abstract class NylonFunction extends NylonObject {
    public abstract NylonObject apply(Stack<NylonObject> stack);

    public boolean toBoolean(Stack<NylonObject> stack) {
        return apply(stack).toDouble(stack) != 0;
    }

    public double toDouble(Stack<NylonObject> stack) {
        return apply(stack).toDouble(stack);
    }

    @Override
    public NylonList toList(Stack<NylonObject> stack) {
        return apply(stack).toList(stack);
    }

    @Override
    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
        return apply(stack).toIterator(stack);
    }

    @Override
    public NylonObject concatenate(NylonObject object, Stack<NylonObject> stack) {
        // TODO
        throw new NylonException("Concatenation of functions is not yet supported", this);
    }

    @Override
    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) {
        throw new NylonException("Cannot subtract functions");
    }
}
