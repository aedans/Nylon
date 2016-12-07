package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonStack extends Stack<NylonObject> implements NylonObject {
    public NylonStack() {
    }

    public NylonStack(NylonObject nylonObject) {
        this.push(nylonObject);
    }

    @Override
    public boolean toBoolean(NylonStack stack) {
        return peek().toBoolean(stack);
    }

    @Override
    public double toDouble(NylonStack stack) {
        return peek().toDouble(stack);
    }

    @Override
    public Iterator<NylonObject> toIterator(NylonStack stack) {
        return iterator();
    }

    @Override
    public NylonStack toStack(NylonStack stack) {
        return this;
    }

    @Override
    public synchronized String toString() {
        for (NylonObject nylonObject : this) {
            if (nylonObject.getClass() != NylonCharacter.class) {
                return super.toString();
            }
        }
        String s = "";
        for (NylonObject nylonObject : this) {
            s += nylonObject.toString();
        }
        return s;
    }
}
