package nylon.builtins;

import nylon.NylonObject;
import nylon.parser.parsers.BuiltinParser;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class Functions {
    public static void build() {
        BuiltinParser.builtins.put(':', new BuiltinFunction(':') {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                return stack.peek().toFunction(stack).apply(stack);
            }
        });
    }
}
