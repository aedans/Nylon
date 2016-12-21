package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonCharacter extends NylonObject<Character> {
    public NylonCharacter(char c) {
        super(c, Type.CHAR);
        this.shouldOutputNewline = false;
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return this.value;
    }

    @Override
    public long toLong(Stack<NylonObject> stack) {
        return this.value;
    }

    @Override
    public char toCharacter(Stack<NylonObject> stack) {
        return this.value;
    }

    @Override
    public NylonCharacter concatenate(NylonObject object, Stack<NylonObject> stack) {
        return new NylonCharacter((char) (this.value + object.toCharacter(stack)));
    }

    @Override
    public NylonCharacter subtract(NylonObject object, Stack<NylonObject> stack) {
        return new NylonCharacter((char) (this.value - object.toCharacter(stack)));
    }

    @Override
    public NylonCharacter clone() {
        return new NylonCharacter(this.value);
    }
}