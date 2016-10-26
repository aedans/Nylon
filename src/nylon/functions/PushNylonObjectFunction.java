package nylon.functions;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class PushNylonObjectFunction<T extends NylonObject> extends NylonFunction {

    private T t;

    public PushNylonObjectFunction(T t) {
        super(0);
        this.t = t;
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        returnStack.add(t.clone());
    }

}
