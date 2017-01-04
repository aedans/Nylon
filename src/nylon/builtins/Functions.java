package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.parser.parsers.BuiltinParser;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class Functions {
    public static void build() {
        BuiltinParser.builtins.put(',', new BuiltinFunction(',') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.pop().toFunction(stack).apply(stack);
            }
        });
    }
}
