package nylon.functions;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonInteger;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

class PushIntegerFunction extends NylonFunction {

    private int toPush;

    PushIntegerFunction(int toPush) {
        super(0);
        this.toPush = toPush;
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        returnStack.add(new NylonInteger(toPush));
    }

}
