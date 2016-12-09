package nylon.builtins;

import nylon.NylonObject;
import nylon.nylonobjects.NylonDouble;
import nylon.parser.parsers.BuiltinParser;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class Math {
    public static void build() {
        BuiltinParser.builtins.put('+', new BuiltinFunction('+') {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.concatenate(n2, stack);
                stack.add(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('-', new BuiltinFunction('-') {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.subtract(n2, stack);
                stack.add(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('*', new BuiltinFunction('*') {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonDouble nylonDouble = new NylonDouble(n1.toDouble(stack) * n2.toDouble(stack));
                stack.add(nylonDouble);
                return nylonDouble;
            }
        });
        BuiltinParser.builtins.put('/', new BuiltinFunction('/') {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonDouble nylonDouble = new NylonDouble(n1.toDouble(stack) / n2.toDouble(stack));
                stack.add(nylonDouble);
                return nylonDouble;
            }
        });
    }
}
