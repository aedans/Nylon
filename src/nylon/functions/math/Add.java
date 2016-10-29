package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Add extends NylonFunction {

    public Add() {
        super(2);
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot add null arguments.");
        else if (args.size() == 1)
            returnStack.add(args.get(0).increment());
        else if (args.size() == 2)
            returnStack.add(args.lastElement().concatenate(args.firstElement()));
        else
            // Should never hit
            throw new NylonRuntimeException("Internal error: Too many arguments given.");
    }

}
