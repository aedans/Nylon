package nylon.builtins;

import nylon.NylonObject;
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
            public NylonObject apply(NylonStack stack) {
                double d1 = stack.pop().toDouble(stack);
                double d2 = stack.pop().toDouble(stack);
                NylonDouble nylonDouble = new NylonDouble(d2 + d1);
                stack.add(nylonDouble);
                return nylonDouble;
            }
        });
        BuiltinParser.builtins.put('-', new BuiltinFunction('-') {
            @Override
            public NylonObject apply(NylonStack stack) {
                double d1 = stack.pop().toDouble(stack);
                double d2 = stack.pop().toDouble(stack);
                NylonDouble nylonDouble = new NylonDouble(d2 - d1);
                stack.add(nylonDouble);
                return nylonDouble;
            }
        });
        BuiltinParser.builtins.put('*', new BuiltinFunction('*') {
            @Override
            public NylonObject apply(NylonStack stack) {
                double d1 = stack.pop().toDouble(stack);
                double d2 = stack.pop().toDouble(stack);
                NylonDouble nylonDouble = new NylonDouble(d2 * d1);
                stack.add(nylonDouble);
                return nylonDouble;
            }
        });
        BuiltinParser.builtins.put('/', new BuiltinFunction('/') {
            @Override
            public NylonObject apply(NylonStack stack) {
                double d1 = stack.pop().toDouble(stack);
                double d2 = stack.pop().toDouble(stack);
                NylonDouble nylonDouble = new NylonDouble(d2 / d1);
                stack.add(nylonDouble);
                return nylonDouble;
            }
        });
    }
}
