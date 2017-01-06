package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class EmptyFunction extends NylonFunction {
    public EmptyFunction() {
        super("EmptyFunction");
    }

    @Override
    public void applyImpl(Stack<NylonObject> stack) throws NylonException {

    }

    @Override
    public String toString() {
        return "EmptyFunction";
    }
}
