package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Add extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (args.size() == 0)
            returnStack.add(new NylonCharacter('+'));
        else if (args.size() == 1)
            returnStack.add(args.pop().increment());
        else {
            NylonObject[] vars = args.pop(2);
            returnStack.add(vars[0].concatenate(vars[1]));
        }
    }

}
