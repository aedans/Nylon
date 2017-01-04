package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public abstract class LibraryFunction extends NylonFunction {
    public LibraryFunction(String s) {
        this.id = s;
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
