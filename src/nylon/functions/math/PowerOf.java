package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonDouble;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class PowerOf extends NylonFunction {

    public PowerOf() {
        super(2);
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot exponentiate null arguments.");
        else if (args.size() == 1)
            throw new NylonRuntimeException("Cannot exponentiate one argument.");
        else if (args.size() == 2)
            returnStack.add(new NylonDouble((int) Math.pow(args.getLast().toDouble(), args.getFirst().toDouble())));
        else
            // Should never hit
            throw new NylonRuntimeException("Internal error: Too many arguments given.");
    }

}
