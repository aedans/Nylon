package nylon.builtins;

import nylon.NylonObject;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonStack;
import nylon.parser.parsers.BuiltinParser;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class Stack {
    private static LinkedList<NylonStack> stacks = new LinkedList<>();

    public static void build() {
        BuiltinParser.builtins.put('(', new BuiltinFunction('(') {
            @Override
            public NylonObject apply(NylonStack stack) {
                Stack.stacks.push((NylonStack) stack.clone());
                stack.clear();
                return stack;
            }
        });
        BuiltinParser.builtins.put(')', new BuiltinFunction(')') {
            @Override
            public NylonObject apply(NylonStack stack) {
                stack.clear();
                stack.addAll(Stack.stacks.pop());
                return stack;
            }
        });
        BuiltinParser.builtins.put('_', new BuiltinFunction('_') {
            @Override
            public NylonObject apply(NylonStack stack) {
                NylonStack nylonStack = (NylonStack) stack.clone();
                stack.add(nylonStack);
                return nylonStack;
            }
        });
        BuiltinParser.builtins.put('`', new BuiltinFunction('`') {
            @Override
            public NylonObject apply(NylonStack stack) {
                return stack.pop();
            }
        });
        BuiltinParser.builtins.put('$', new BuiltinFunction('$') {
            @Override
            public NylonObject apply(NylonStack stack) {
                NylonStack nylonStack = (NylonStack) stack.clone();
                NylonObject nylonObject = nylonStack.pop();
                stack.clear();
                stack.add(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put(':', new BuiltinFunction(':') {
            @Override
            public NylonObject apply(NylonStack stack) {
                NylonObject nylonObject = stack.peek();
                stack.add(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put(';', new BuiltinFunction(';') {
            @Override
            public NylonObject apply(NylonStack stack) {
                stack.addAll((NylonStack) stack.clone());
                return stack;
            }
        });
        BuiltinParser.builtins.put('À', new BuiltinFunction('À') {
            @Override
            public NylonObject apply(NylonStack stack) {
                NylonObject nylonObject = Stack.stacks.peek().pop();
                stack.push(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('Á', new BuiltinFunction('Á') {
            @Override
            public NylonObject apply(NylonStack stack) {
                NylonObject nylonObject = stack.pop();
                Stack.stacks.peek().push(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('ì', new BuiltinFunction('ì') {
            @Override
            public NylonObject apply(NylonStack stack) {
                NylonObject nylonObject = stack.pop();
                stack.insertElementAt(nylonObject, 0);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('í', new BuiltinFunction('í') {
            @Override
            public NylonObject apply(NylonStack stack) {
                NylonObject nylonObject = stack.remove(0);
                stack.push(nylonObject);
                return nylonObject;
            }
        });
        BuiltinParser.builtins.put('î', new BuiltinFunction('î') {
            @Override
            public NylonObject apply(NylonStack stack) {
                NylonDouble nylonDouble = new NylonDouble(stack.size());
                stack.push(nylonDouble);
                return nylonDouble;
            }
        });
    }
}
