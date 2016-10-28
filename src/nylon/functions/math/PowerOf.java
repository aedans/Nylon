package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonDouble;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class PowerOf extends NylonFunction {

    public PowerOf() {
        super(2);
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot exponentiate null arguments.");
        else if (args.size() == 1)
            throw new NylonRuntimeException("Cannot exponentiate one argument.");
        else if (args.size() == 2)
            returnStack.add(new NylonDouble((int) Math.pow(args.lastElement().toDouble(), args.get(0).toDouble())));
        else
            // Should never hit
            throw new NylonRuntimeException("Internal error: Too many arguments given.");
    }

}
