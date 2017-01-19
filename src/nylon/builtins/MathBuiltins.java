package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.BuiltinFunction;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonLong;
import nylon.parser.Parser;
import nylon.parser.parsers.BuiltinParser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class MathBuiltins {
    public static void build(ArrayList<Parser> parsers) {
        parsers.set('+', BuiltinParser.getParser(new BuiltinFunction('+') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.promote(n2).concatenate(n2.promote(n1));
                stack.add(nylonObject);
            }
        }));
        parsers.set('-', BuiltinParser.getParser(new BuiltinFunction('-') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.promote(n2).subtract(n2.promote(n1));
                stack.add(nylonObject);
            }
        }));
        parsers.set('*', BuiltinParser.getParser(new BuiltinFunction('*') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.promote(n2).multiply(n2.promote(n1));
                stack.add(nylonObject);
            }
        }));
        parsers.set('/', BuiltinParser.getParser(new BuiltinFunction('/') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonObject nylonObject = n1.promote(n2).divide(n2.promote(n1));
                stack.add(nylonObject);
            }
        }));
        parsers.set('^', BuiltinParser.getParser(new BuiltinFunction('^') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonDouble nylonDouble = new NylonDouble(Math.pow(n1.toDouble(), n2.toDouble()));
                stack.add(nylonDouble);
            }
        }));
        parsers.set('%', BuiltinParser.getParser(new BuiltinFunction('%') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonObject n2 = stack.pop(), n1 = stack.pop();
                NylonLong nylonLong = new NylonLong(n1.toLong() % n2.toLong());
                stack.add(nylonLong);
            }
        }));
    }
}
