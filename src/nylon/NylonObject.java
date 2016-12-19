package nylon;

import nylon.nylonobjects.NylonArray;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonLong;
import nylon.nylonobjects.NylonString;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 *
 * Class for handling Objects on the Nylon stack.
 */

public abstract class NylonObject<T> {
    public T value;

    public NylonObject(T value) {
        this.value = value;
    }

    public abstract double toDouble(Stack<NylonObject> stack);

    public boolean toBoolean(Stack<NylonObject> stack) {
        return toDouble(stack) != 0;
    }

    public long toLong(Stack<NylonObject> stack) {
        return (long) toDouble(stack);
    }

    public char toCharacter(Stack<NylonObject> stack) {
        return (char) toDouble(stack);
    }

    public NylonArray toArray(Stack<NylonObject> stack) {
        return new NylonArray(this);
    }

    public NylonFunction toFunction(Stack<NylonObject> stack) {
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

    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
        return new Iterator<NylonObject>() {
            long i = 0, number = toLong(stack);

            @Override
            public boolean hasNext() {
                return i < number;
            }

            @Override
            public NylonObject next() {
                return new NylonLong(i++);
            }
        };
    }

    public NylonString toNylonString(Stack<NylonObject> stack) {
        return new NylonString(this.toString().toCharArray());
    }

    public NylonObject concatenate(NylonObject object, Stack<NylonObject> stack) {
        throw new NylonException("Could not concatenate objects");
    }

    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) {
        throw new NylonException("Could not subtract objects");
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NylonObject && this.value.equals(((NylonObject) obj).value);
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
