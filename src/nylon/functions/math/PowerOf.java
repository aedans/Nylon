package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonDouble;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class PowerOf extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot exponentiate null arguments.");
        else if (args.size() == 1)
            throw new NylonRuntimeException("Cannot exponentiate one argument.");
        else {
            NylonObject[] vars = args.pop(2);
            returnStack.add(new NylonDouble(Math.pow(vars[0].toDouble(), vars[1].toDouble())));
        }
    }

}
