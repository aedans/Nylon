package nylon.functions.ifstatements;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonDouble;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public abstract class Conditional extends NylonFunction {

    public abstract boolean toBoolean(NylonStack args) throws NylonRuntimeException;

    @Override
    public Conditional clone() {
        return (Conditional) super.clone();
    }

    @Override
    public Iterator<NylonObject> iterator(NylonStack nylonStack) throws NylonRuntimeException {
        return new Iterator<NylonObject>() {
            @Override
            public boolean hasNext() {
                try {
                    return toBoolean(nylonStack);
                } catch (NylonRuntimeException e) {
                    return false;
                }
            }

            @Override
            public NylonObject next() {
                return new NylonDouble(1);
            }
        };
    }

    @Override
    public Iterator<NylonObject> reverseIterator(NylonStack nylonStack) throws NylonRuntimeException {
        return iterator(nylonStack);
    }
}
