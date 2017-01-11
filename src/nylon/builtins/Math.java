package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.BuiltinFunction;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonLong;
import nylon.parser.parsers.BuiltinParser;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class Math {
    public static void build() {
        BuiltinParser.builtins.put('+', new BuiltinFunction('+') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.promote(n2, stack).concatenate(n2.promote(n1, stack), stack);
                stack.add(nylonObject);
            }
        });
        BuiltinParser.builtins.put('-', new BuiltinFunction('-') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.promote(n2, stack).subtract(n2.promote(n1, stack), stack);
                stack.add(nylonObject);
            }
        });
        BuiltinParser.builtins.put('*', new BuiltinFunction('*') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.promote(n2, stack).multiply(n2.promote(n1, stack), stack);
                stack.add(nylonObject);
            }
        });
        BuiltinParser.builtins.put('/', new BuiltinFunction('/') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.promote(n2, stack).divide(n2.promote(n1, stack), stack);
                stack.add(nylonObject);
            }
        });
        BuiltinParser.builtins.put('^', new BuiltinFunction('^') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonDouble nylonDouble = new NylonDouble(java.lang.Math.pow(n1.toDouble(stack), n2.toDouble(stack)));
                stack.add(nylonDouble);
            }
        });
        BuiltinParser.builtins.put('%', new BuiltinFunction('%') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonLong nylonLong = new NylonLong(n1.toLong(stack) % n2.toLong(stack));
                stack.add(nylonLong);
            }
        });
    }
}
