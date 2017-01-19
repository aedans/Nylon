package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class NylonCharacter extends NylonObject<Character> {
    public NylonCharacter(char c) {
        super(c, Type.CHAR, "Character");
        this.shouldOutputNewline = false;
    }

    @Override
    public double toDouble() {
        return this.value;
    }

    @Override
    public long toLong() {
        return this.value;
    }

    @Override
    public char toCharacter() {
        return this.value;
    }

    @Override
    public Iterator<NylonObject> toIterator() throws NylonException {
        return new Iterator<NylonObject>() {
            char l = 0;

            @Override
            public boolean hasNext() {
                return l < value;
            }

            @Override
            public NylonObject next() {
                return new NylonCharacter(l++);
            }
        };
    }

    @Override
    public NylonCharacter concatenate(NylonObject object) throws NylonException {
        this.value = (char) (this.value + object.toCharacter());
        return this;
    }

    @Override
    public NylonCharacter subtract(NylonObject object) throws NylonException {
        this.value = (char) (this.value - object.toCharacter());
        return this;
    }


    @Override
    public NylonCharacter multiply(NylonObject object) throws NylonException {
        this.value = (char) (this.value * object.toCharacter());
        return this;
    }

    @Override
    public NylonObject divide(NylonObject object) throws NylonException {
        this.value = (char) (this.value / object.toCharacter());
        return this;
    }

    @Override
    public NylonCharacter clone() {
        return new NylonCharacter(this.value);
    }
}