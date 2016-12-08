package nylon.builtins;

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
            public NylonStack apply(NylonStack stack) {
                Stack.stacks.push((NylonStack) stack.clone());
                stack.clear();
                return stack;
            }
        });
        BuiltinParser.builtins.put(')', new BuiltinFunction(')') {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.clear();
                stack.addAll(Stack.stacks.pop());
                return stack;
            }
        });
        BuiltinParser.builtins.put('_', new BuiltinFunction('_') {
            @Override
            public NylonStack apply(NylonStack stack) {
                NylonStack nylonStack = (NylonStack) stack.clone();
                stack.add(nylonStack);
                return nylonStack;
            }
        });
        BuiltinParser.builtins.put('`', new BuiltinFunction('`') {
            @Override
            public NylonStack apply(NylonStack stack) {
                return stack.pop().toStack(stack);
            }
        });
        BuiltinParser.builtins.put('$', new BuiltinFunction('$') {
            @Override
            public NylonStack apply(NylonStack stack) {
                NylonStack nylonStack = (NylonStack) stack.clone();
                stack.clear();
                stack.add(nylonStack.pop());
                return nylonStack;
            }
        });
        BuiltinParser.builtins.put(':', new BuiltinFunction(':') {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.add(stack.peek());
                return stack.peek().toStack(stack);
            }
        });
        BuiltinParser.builtins.put(';', new BuiltinFunction(';') {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.addAll((NylonStack) stack.clone());
                return stack;
            }
        });
        BuiltinParser.builtins.put('À', new BuiltinFunction('À') {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.push(Stack.stacks.peek().pop());
                return stack.peek().toStack(stack);
            }
        });
        BuiltinParser.builtins.put('Á', new BuiltinFunction('Á') {
            @Override
            public NylonStack apply(NylonStack stack) {
                Stack.stacks.peek().push(stack.pop());
                return Stack.stacks.peek().toStack(stack);
            }
        });
        BuiltinParser.builtins.put('ì', new BuiltinFunction('ì') {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.insertElementAt(stack.pop(), 0);
                return stack.get(0).toStack(stack);
            }
        });
        BuiltinParser.builtins.put('í', new BuiltinFunction('í') {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.push(stack.remove(0));
                return stack.peek().toStack(stack);
            }
        });
        BuiltinParser.builtins.put('î', new BuiltinFunction('î') {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.push(new NylonDouble(stack.size()));
                return stack.peek().toStack(stack);
            }
        });
        BuiltinParser.builtins.put('×', new BuiltinFunction('×') {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.add(stack.get((int) stack.pop().toDouble(stack)));
                return stack.peek().toStack(stack);
            }
        });
    }
}
