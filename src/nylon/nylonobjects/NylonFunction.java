package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.util.Iterator;
import java.util.Stack;
import java.util.function.Function;

/**
 * Created by Aedan Smith.
 */

@SuppressWarnings("unchecked")
public abstract class NylonFunction extends NylonObject<Function<Stack<NylonObject>, NylonObject>>
        implements Function<Stack<NylonObject>, NylonObject> {
    public NylonFunction() {
        super(null, Type.FUNCTION);
        this.value = this;
    }

    @Override
    public boolean toBoolean(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toBoolean(stack);
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toDouble(stack);
    }

    @Override
    public long toLong(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toLong(stack);
    }

    @Override
    public char toCharacter(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toCharacter(stack);
    }

    @Override
    public NylonArray toArray(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toArray(stack);
    }

    @Override
    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toIterator(stack);
    }

    @Override
    public NylonFunction toFunction(Stack<NylonObject> stack) {
        return this;
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
