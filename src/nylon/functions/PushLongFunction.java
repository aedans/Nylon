package nylon.functions;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonLong;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class PushLongFunction extends NylonFunction {

    private long toPush;

    public PushLongFunction(long toPush) {
        super(0);
        this.toPush = toPush;
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        returnStack.add(new NylonLong(toPush));
    }

}
