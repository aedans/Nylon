package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonArray;
import nylon.nylonobjects.NylonDouble;
import nylon.parser.parsers.BuiltinParser;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public final class Stack {
    private static LinkedList<java.util.Stack<NylonObject>> stacks = new LinkedList<>();

    public static void build() {
        BuiltinParser.builtins.put('(', new BuiltinFunction('(') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                Stack.stacks.push((java.util.Stack<NylonObject>) stack.clone());
                stack.clear();
            }
        });
        BuiltinParser.builtins.put(')', new BuiltinFunction(')') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                stack.clear();
                stack.addAll(Stack.stacks.pop());
            }
        });
        BuiltinParser.builtins.put('_', new BuiltinFunction('_') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonArray nylonStack = new NylonArray((java.util.Stack<NylonObject>) stack.clone());
                stack.clear();
                stack.add(nylonStack);
            }
        });
        BuiltinParser.builtins.put(';', new BuiltinFunction(';') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                stack.pop();
            }
        });
        BuiltinParser.builtins.put(':', new BuiltinFunction(':') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) throws NylonException {
                NylonObject object = stack.pop();
                try {
                    stack.add(object.clone());
                } catch (CloneNotSupportedException e) {
                    throw new NylonException("Could not clone object \"" + object.getId() + "\"", this);
                }
            }
        });
        BuiltinParser.builtins.put('À', new BuiltinFunction('À') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = Stack.stacks.peek().pop();
                stack.push(nylonObject);
            }
        });
        BuiltinParser.builtins.put('Á', new BuiltinFunction('Á') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.pop();
                Stack.stacks.peek().push(nylonObject);
            }
        });
        BuiltinParser.builtins.put('ù', new BuiltinFunction('ù') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.pop();
                stack.insertElementAt(nylonObject, 0);
            }
        });
        BuiltinParser.builtins.put('ú', new BuiltinFunction('ú') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.remove(0);
                stack.push(nylonObject);
            }
        });
        BuiltinParser.builtins.put('Ï', new BuiltinFunction('Ï') {
            @Override
            public void applyImpl(java.util.Stack<NylonObject> stack) {
                NylonDouble nylonDouble = new NylonDouble(stack.size());
                stack.push(nylonDouble);
            }
        });
    }
}
