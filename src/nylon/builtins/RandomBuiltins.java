package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.LibraryFunction;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonLong;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public final class RandomBuiltins {
    public static void build(HashMap<String, Supplier<NylonFunction>> functions) {
        functions.put("Rd", () -> new LibraryFunction("Rd.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.push(new NylonDouble(new Random().nextDouble()));
            }
        });
        functions.put("Rl", () -> new LibraryFunction("Rl.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.push(new NylonLong(new Random().nextLong()));
            }
        });
    }
}
