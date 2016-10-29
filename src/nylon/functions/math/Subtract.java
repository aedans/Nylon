package nylon.functions.math;

import javafx.util.Pair;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonDouble;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Subtract extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot subtract null arguments.");
        else if (args.size() == 1)
            returnStack.add(args.pop().decrement());
        else {
            NylonObject[] vars = args.pop(2);
            returnStack.add(new NylonDouble(vars[0].toDouble() - vars[1].toDouble()));
        }
    }

}
