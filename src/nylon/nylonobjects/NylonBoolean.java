package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonBoolean extends NylonObject<Boolean> {
    public NylonBoolean(boolean b) {
        super(b, Type.BOOL, "Boolean");
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return this.value ? 1 : 0;
    }

    @Override
    public boolean toBoolean(Stack<NylonObject> stack) throws NylonException {
        return this.value;
    }
}
