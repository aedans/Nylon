package nylon.functions;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.ifstatements.Conditional;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class PushNylonObjectFunction<T extends NylonObject> extends Conditional {

    private T t;

    public PushNylonObjectFunction(T t) {
        super(0);
        this.t = t;
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        returnStack.add(t.clone());
    }

    @Override
    public String toString() {
        return super.toString() + "[" + t.toString() + "]";
    }

    @Override
    public boolean toBoolean(NylonStack args) throws NylonRuntimeException {
        return t.toBoolean();
    }
}
