package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.NylonFunction;
import nylon.objects.NylonLong;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class Multiply extends NylonFunction {

    public Multiply() {
        super(2);
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot multiply null arguments.");
        else if (args.size() == 1)
            throw new NylonRuntimeException("Cannot multiply one argument.");
        else if (args.size() == 2)
            returnStack.add(new NylonLong(args.getFirst().toLong() * args.getLast().toLong()));
        else
            // Should never hit
            throw new NylonRuntimeException("Internal error: Too many arguments given.");
    }
}
