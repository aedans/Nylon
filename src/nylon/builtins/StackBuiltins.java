package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.BuiltinFunction;
import nylon.nylonobjects.NylonDouble;
import nylon.parser.Parser;
import nylon.parser.parsers.BuiltinParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class StackBuiltins {
    private static LinkedList<Stack<NylonObject>> stacks = new LinkedList<>();

    public static void build(ArrayList<Parser> parsers) {
        parsers.set('(', BuiltinParser.getParser(new BuiltinFunction('(') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                StackBuiltins.stacks.push((Stack<NylonObject>) stack.clone());
                stack.clear();
            }
        }));
        parsers.set(')', BuiltinParser.getParser(new BuiltinFunction(')') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                stack.clear();
                stack.addAll(StackBuiltins.stacks.pop());
            }
        }));
        parsers.set(':', BuiltinParser.getParser(new BuiltinFunction(':') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
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
            public void applyImpl(Stack<NylonObject> stack) {
                NylonObject nylonObject = StackBuiltins.stacks.peek().pop();
                stack.push(nylonObject);
            }
        }));
        parsers.set('Á', BuiltinParser.getParser(new BuiltinFunction('Á') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.pop();
                StackBuiltins.stacks.peek().push(nylonObject);
            }
        }));
        parsers.set('ù', BuiltinParser.getParser(new BuiltinFunction('ù') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.pop();
                stack.insertElementAt(nylonObject, 0);
            }
        }));
        parsers.set('ú', BuiltinParser.getParser(new BuiltinFunction('ú') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                NylonObject nylonObject = stack.remove(0);
                stack.push(nylonObject);
            }
        }));
        parsers.set('Ï', BuiltinParser.getParser(new BuiltinFunction('Ï') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                NylonDouble nylonDouble = new NylonDouble(stack.size());
                stack.push(nylonDouble);
            }
        }));
    }
}