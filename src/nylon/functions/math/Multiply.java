package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonDouble;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Multiply extends NylonFunction {

    public Multiply() {
        super(2);
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot multiply null arguments.");
        else if (args.size() == 1)
            throw new NylonRuntimeException("Cannot multiply one argument.");
        else if (args.size() == 2)
            returnStack.add(new NylonDouble(args.getFirst().toDouble() * args.getLast().toDouble()));
        else
            // Should never hit
            throw new NylonRuntimeException("Internal error: Too many arguments given.");
    }
}
