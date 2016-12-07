package nylon.builtins;

import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonStack;
import nylon.parser.parsers.BuiltinParser;

/**
 * Created by Aedan Smith.
 */

public final class Math {
    public static void build() {
        BuiltinParser.builtins.put('+', new BuiltinFunction('+') {
            @Override
            public NylonStack apply(NylonStack stack) {
                double d1 = stack.pop().toDouble(stack);
                double d2 = stack.pop().toDouble(stack);
                stack.add(new NylonDouble(d2 + d1));
                return stack.peek().toStack(stack);
            }
        });
        BuiltinParser.builtins.put('-', new BuiltinFunction('-') {
            @Override
            public NylonStack apply(NylonStack stack) {
                double d1 = stack.pop().toDouble(stack);
                double d2 = stack.pop().toDouble(stack);
                stack.add(new NylonDouble(d2 - d1));
                return stack.peek().toStack(stack);
            }
        });
        BuiltinParser.builtins.put('*', new BuiltinFunction('*') {
            @Override
            public NylonStack apply(NylonStack stack) {
                double d1 = stack.pop().toDouble(stack);
                double d2 = stack.pop().toDouble(stack);
                stack.add(new NylonDouble(d2 * d1));
                return stack.peek().toStack(stack);
            }
        });
        BuiltinParser.builtins.put('/', new BuiltinFunction('/') {
            @Override
            public NylonStack apply(NylonStack stack) {
                double d1 = stack.pop().toDouble(stack);
                double d2 = stack.pop().toDouble(stack);
                stack.add(new NylonDouble(d2 / d1));
                return stack.peek().toStack(stack);
            }
        });
    }
}
