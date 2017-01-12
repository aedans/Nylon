package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.BuiltinFunction;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;
import nylon.parser.parsers.BuiltinParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public final class Stack {
    private static LinkedList<java.util.Stack<NylonObject>> stacks = new LinkedList<>();

    public static void build(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
        parsers.set('(', BuiltinParser.getParser(new BuiltinFunction('(') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                Stack.stacks.push((java.util.Stack<NylonObject>) stack.clone());
                stack.clear();
            }
        }));
        parsers.set(')', BuiltinParser.getParser(new BuiltinFunction(')') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                stack.clear();
                stack.addAll(Stack.stacks.pop());
            }
        }));
        parsers.set(':', BuiltinParser.getParser(new BuiltinFunction(':') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) throws NylonException {
                NylonObject object = stack.pop();
                try {
                    stack.add(object.clone());
                    stack.add(object.clone());
                } catch (CloneNotSupportedException e) {
                    throw new NylonException("Could not clone object \"" + object.getId() + "\"", this);
                }
            }
        }));
        parsers.set('À', BuiltinParser.getParser(new BuiltinFunction('À') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = Stack.stacks.peek().pop();
                stack.push(nylonObject);
            }
        }));
        parsers.set('Á', BuiltinParser.getParser(new BuiltinFunction('Á') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.pop();
                Stack.stacks.peek().push(nylonObject);
            }
        }));
        parsers.set('ù', BuiltinParser.getParser(new BuiltinFunction('ù') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.pop();
                stack.insertElementAt(nylonObject, 0);
            }
        }));
        parsers.set('ú', BuiltinParser.getParser(new BuiltinFunction('ú') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.remove(0);
                stack.push(nylonObject);
            }
        }));
        parsers.set('Ï', BuiltinParser.getParser(new BuiltinFunction('Ï') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonDouble nylonDouble = new NylonDouble(stack.size());
                stack.push(nylonDouble);
            }
        }));
    }
}
