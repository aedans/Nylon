package nylon.builtins;

import nylon.nylonobjects.NylonFunction;

/**
 * Created by Aedan Smith.
 */

public abstract class LibraryFunction extends NylonFunction {
    private String s;

    public LibraryFunction(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "LibraryFunction(\"" + s + "\")";
    }
}
