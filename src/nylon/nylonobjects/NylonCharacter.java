package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonCharacter extends NylonObject<Character> {
    public NylonCharacter(char c) {
        super(c, Type.CHAR, "Character");
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
    public NylonCharacter concatenate(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        this.value = (char) (this.value + object.toCharacter(stack));
        return this;
    }

    @Override
    public NylonCharacter subtract(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        this.value = (char) (this.value - object.toCharacter(stack));
        return this;
    }


    @Override
    public NylonCharacter multiply(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        this.value = (char) (this.value * object.toCharacter(stack));
        return this;
    }

    @Override
    public NylonObject divide(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        this.value = (char) (this.value / object.toCharacter(stack));
        return this;
    }

    @Override
    public NylonCharacter clone() {
        return new NylonCharacter(this.value);
    }
}