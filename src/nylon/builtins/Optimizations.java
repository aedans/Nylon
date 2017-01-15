package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.LibraryFunction;
import nylon.nylonobjects.NylonFunction;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public final class Optimizations {
    public static void build(HashMap<String, Supplier<NylonFunction>> functions) {
        functions.put("p", () -> new LibraryFunction("p.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.pop();
            }
        });
    }
}
