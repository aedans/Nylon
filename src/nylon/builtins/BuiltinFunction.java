package nylon.builtins;

import nylon.nylonobjects.NylonFunction;

/**
 * Created by Aedan Smith.
 */

public abstract class BuiltinFunction extends NylonFunction {
    private char c;

    public BuiltinFunction(char c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "BuiltinFunction('" + c + "')";
    }
}
