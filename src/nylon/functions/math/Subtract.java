package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonDouble;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Subtract extends NylonFunction {

    public Subtract() {
        super(2);
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot subtract null arguments.");
        else if (args.size() == 1)
            returnStack.add(args.get(0).decrement());
        else if (args.size() == 2)
            returnStack.add(new NylonDouble(args.get(0).toDouble() - args.lastElement().toDouble()));
        else
            // Should never hit
            throw new NylonRuntimeException("Internal error: Too many arguments given.");
    }

}
