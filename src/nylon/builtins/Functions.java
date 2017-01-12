package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.BuiltinFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;
import nylon.parser.parsers.BuiltinParser;

import java.util.ArrayList;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public final class Functions {
    public static void build(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
        parsers.set(',', BuiltinParser.getParser(new BuiltinFunction(',') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.pop().toFunction(stack).apply(stack);
            }
        }));
    }
}
