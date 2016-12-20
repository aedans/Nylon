package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonBoolean extends NylonObject<Boolean> {
    public NylonBoolean(boolean b) {
        super(b, Type.BOOL);
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return this.value ? 1 : 0;
    }
}
