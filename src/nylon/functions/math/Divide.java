package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonDouble;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Divide extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot divide null arguments.");
        else if (args.size() == 1)
            throw new NylonRuntimeException("Cannot divide one argument.");
        else {
            NylonObject[] vars = args.pop(2);
            returnStack.add(new NylonDouble(vars[0].toDouble() / vars[1].toDouble()));
        }
    }

}
