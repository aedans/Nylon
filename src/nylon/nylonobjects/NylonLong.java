package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class NylonLong extends NylonObject<Long> {
    public NylonLong(long l) {
        super(l, Type.LONG, "Long");
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
    public Iterator<NylonObject> toIterator() throws NylonException {
        return new Iterator<NylonObject>() {
            long l = 0;

            @Override
            public boolean hasNext() {
                return l < value;
            }

            @Override
            public NylonObject next() {
                return new NylonLong(l++);
            }
        };
    }

    @Override
    public NylonLong concatenate(NylonObject object) throws NylonException {
        this.value += object.toLong();
        return this;
    }

    @Override
    public NylonLong subtract(NylonObject object) throws NylonException {
        this.value -= object.toLong();
        return this;
    }

    @Override
    public NylonLong multiply(NylonObject object) throws NylonException {
        this.value *= object.toLong();
        return this;
    }

    @Override
    public NylonLong divide(NylonObject object) throws NylonException {
        this.value /= object.toLong();
        return this;
    }

    @Override
    public NylonLong clone() {
        return new NylonLong(this.value);
    }
}
