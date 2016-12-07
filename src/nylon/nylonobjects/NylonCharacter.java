package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class NylonCharacter implements NylonObject {
    private char c;

    public NylonCharacter(char c) {
        this.c = c;
    }

    @Override
    public boolean toBoolean(NylonStack stack) {
        return c == 't';
    }

    @Override
    public double toDouble(NylonStack stack) {
        return c;
    }

    @Override
    public Iterator<NylonObject> toIterator(NylonStack stack) {
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
    public NylonStack toStack(NylonStack stack) {
        return new NylonStack(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NylonCharacter && ((NylonCharacter) obj).c == c;
    }

    @Override
    public String toString() {
        return String.valueOf(c);
    }
}
