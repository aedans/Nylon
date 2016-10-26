package nylon.functions;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonObject;
import nylon.objects.NylonString;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class PushStringFunction extends NylonFunction {

    private String toPush;

    public PushStringFunction(String toPush) {
        super(0);
        this.toPush = toPush;
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack) throws NylonRuntimeException {
        returnStack.add(new NylonString(toPush));
    }

}
