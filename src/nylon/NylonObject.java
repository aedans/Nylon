package nylon;

import nylon.nylonobjects.NylonList;

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

    public abstract Iterator<NylonObject> toIterator(Stack<NylonObject> stack);

    public abstract NylonList toList(Stack<NylonObject> stack);

    public abstract NylonObject concatenate(NylonObject object, Stack<NylonObject> stack);

    public abstract NylonObject subtract(NylonObject object, Stack<NylonObject> stack);

    @Override
    public NylonObject clone() {
        try {
            return (NylonObject) super.clone();
        } catch (Exception e) {
            throw new NylonException("Internal Error: Could not clone object.", this);
        }
    }
}
