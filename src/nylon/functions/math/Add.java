package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.NylonFunction;
import nylon.objects.NylonInteger;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class Add extends NylonFunction {

    public Add() {
        super(2);
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot add null arguments.");
        else if (args.size() == 1)
            returnStack.add(args.getFirst().increment());
        else if (args.size() == 2)
            returnStack.add(args.getFirst().concatenate(args.getLast()));
        else
            // Should never hit
            throw new NylonRuntimeException("Internal error: Too many arguments given.");
    }

}
