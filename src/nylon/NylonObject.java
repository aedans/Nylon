package nylon;

import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonList;
import nylon.nylonobjects.NylonString;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public abstract class NylonObject {
    public abstract boolean toBoolean(Stack<NylonObject> stack);

    public abstract double toDouble(Stack<NylonObject> stack);

    public char toCharacter(Stack<NylonObject> stack) {
        return (char) toDouble(stack);
    }

    public NylonObject toFunction(Stack<NylonObject> stack) {
        return new NylonFunction() {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                stack.add(NylonObject.this.clone());
                return stack.peek();
            }

            @Override
            public String toString() {
                return "Push" + NylonObject.this.getClass().getSimpleName() + "(" + NylonObject.this + ")";
            }
        };
    }

    public NylonObject toNylonString(Stack<NylonObject> stack) {
        return new NylonString(this.toString().toCharArray());
    }

    public abstract Iterator<NylonObject> toIterator(Stack<NylonObject> stack);

    public abstract NylonList toList(Stack<NylonObject> stack);

    public NylonObject concatenate(NylonObject object, Stack<NylonObject> stack) {
        return new NylonString((this.toString() + object.toString()).toCharArray());
    }

    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) {
        throw new NylonException("Could not subtract objects");
    }

    @Override
    public NylonObject clone() {
        try {
            return (NylonObject) super.clone();
        } catch (Exception e) {
            throw new NylonException("Internal Error: Could not clone object.", this);
        }
    }
}
