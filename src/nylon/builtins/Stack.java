package nylon.builtins;

import nylon.NylonObject;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonList;
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
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                Stack.stacks.push((java.util.Stack<NylonObject>) stack.clone());
                stack.clear();
                return Stack.stacks.peek().peek();
            }
        });
        BuiltinParser.builtins.put(')', new BuiltinFunction(')') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                stack.clear();
                stack.addAll(Stack.stacks.pop());
                return stack.peek();
            }
        });
        BuiltinParser.builtins.put('_', new BuiltinFunction('_') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                NylonList nylonStack = new NylonList((java.util.Stack<NylonObject>) stack.clone());
                stack.add(nylonStack);
                return nylonStack;
            }
        });
        BuiltinParser.builtins.put('`', new BuiltinFunction('`') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                return stack.pop();
            }
        });
        BuiltinParser.builtins.put('$', new BuiltinFunction('$') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                java.util.Stack<NylonObject> nylonStack = (java.util.Stack<NylonObject>) stack.clone();
                NylonObject nylonObject = nylonStack.pop();
                stack.clear();
                stack.add(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put(':', new BuiltinFunction(':') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.pop();
                stack.add(nylonObject.clone());
                stack.add(nylonObject.clone());
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('À', new BuiltinFunction('À') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = Stack.stacks.peek().pop();
                stack.push(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('Á', new BuiltinFunction('Á') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.pop();
                Stack.stacks.peek().push(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('ù', new BuiltinFunction('ù') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.pop();
                stack.insertElementAt(nylonObject, 0);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('ú', new BuiltinFunction('ú') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.remove(0);
                stack.push(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('Ï', new BuiltinFunction('Ï') {
            @Override
            public NylonObject apply(java.util.Stack<NylonObject> stack) {
                NylonDouble nylonDouble = new NylonDouble(stack.size());
                stack.push(nylonDouble);
                return nylonDouble;
            }
        });
    }
}
