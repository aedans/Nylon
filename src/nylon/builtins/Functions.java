package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.BuiltinFunction;
import nylon.parser.Parser;
import nylon.parser.parsers.BuiltinParser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class Functions {
    public static void build(ArrayList<Parser> parsers) {
        parsers.set(',', BuiltinParser.getParser(new BuiltinFunction(',') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.pop().toFunction(stack).apply(stack);
            }
        }));
    }
}
