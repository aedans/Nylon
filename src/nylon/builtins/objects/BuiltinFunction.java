package nylon.builtins.objects;

import nylon.nylonobjects.NylonFunction;

/**
 * Created by Aedan Smith.
 */

public abstract class BuiltinFunction extends NylonFunction {
    private char c;

    public BuiltinFunction(char c) {
        super("BuiltinFunction('" + c + "')");
        this.c = c;
    }

    @Override
    public String toString() {
        return id;
    }
}
