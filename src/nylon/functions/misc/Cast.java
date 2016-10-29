package nylon.functions.misc;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Cast extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        NylonObject[] vars = args.pop(2);
        returnStack.add(vars[0].cast(vars[1].toChar()));
    }

}
