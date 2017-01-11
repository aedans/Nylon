package nylon.builtins.objects;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public abstract class LibraryFunction extends NylonFunction {
    public LibraryFunction(String s) {
        super(s);
    }

    @Override
    public void apply(Stack<NylonObject> stack) throws NylonException {
        applyImpl(stack);
    }

    @Override
    public String toString() {
        return "LibraryFunction(\"" + id + "\")";
    }
}
