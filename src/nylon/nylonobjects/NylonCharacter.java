package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonCharacter extends NylonObject {
    private char c;

    public NylonCharacter(char c) {
        this.c = c;
    }

    @Override
    public boolean toBoolean(Stack<NylonObject> stack) {
        return c == 't';
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return c;
    }

    @Override
    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
        return new Iterator<NylonObject>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < c;
            }

            @Override
            public NylonObject next() {
                return new NylonCharacter((char) i++);
            }
        };
    }

    @Override
    public NylonList toList(Stack<NylonObject> stack) {
        return new NylonList(this);
    }

    @Override
    public NylonObject concatenate(NylonObject object, Stack<NylonObject> stack) {
        c += object.toCharacter(stack);
        return this;
    }

    @Override
    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) {
        c -= object.toCharacter(stack);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NylonCharacter && ((NylonCharacter) obj).c == c;
    }

    @Override
    public String toString() {
        return String.valueOf(c);
    }

    @Override
    public NylonObject clone() {
        return new NylonCharacter(c);
    }
}