package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Aedan Smith.
 */

public class NylonArray extends NylonObject<Vector<NylonObject>> {
    public NylonArray() {
        super(new Vector<>(), Type.ARRAY, "Array");
        this.shouldOutputNewline = false;
    }

    public NylonArray(NylonObject object) {
        super(new Vector<>(), Type.ARRAY, "Array");
        this.value.add(object);
    }

    public NylonArray(Collection<NylonObject> stack) {
        super(new Vector<>(), Type.ARRAY, "Array");
        this.value.addAll(stack);
    }

    @Override
    public boolean toBoolean() throws NylonException {
        return !value.isEmpty() && this.value.lastElement().toBoolean();
    }

    @Override
    public double toDouble() {
        return value.size();
    }

    @Override
    public Iterator<NylonObject> toIterator() {
        return value.iterator();
    }

    @Override
    public NylonArray toArray() {
        return this;
    }

    @Override
    public NylonString toNylonString() {
        String s = "";
        for (NylonObject nylonObject : this.value) {
            s += nylonObject.toString();
        }
        return new NylonString(s.toCharArray());
    }

    @Override
    public NylonObject concatenate(NylonObject object) throws NylonException {
        value.addAll(object.toArray().value);
        return this;
    }

    @Override
    public NylonObject subtract(NylonObject object) throws NylonException {
        value.removeAll(object.toArray().value);
        return this;
    }

    @Override
    public NylonObject multiply(NylonObject object) throws NylonException {
        Vector<NylonObject> v = new Vector<>();
        for (long i = 0; i < object.toLong(); i++) {
            v.addAll(this.value);
        }
        this.value = v;
        return this;
    }

    @Override
    public String toString() {
        String s = "";
        for (NylonObject nylonObject : this.value) {
            s += nylonObject + "\n";
        }
        return s;
    }

    @Override
    public NylonObject clone() {
        //noinspection unchecked
        return new NylonArray((Collection<NylonObject>) this.value.clone());
    }
}
