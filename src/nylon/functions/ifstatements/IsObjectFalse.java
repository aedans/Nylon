package nylon.functions.ifstatements;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.FunctionSkipObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class IsObjectFalse extends Conditional {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (toBoolean(args)) {
            returnStack.add(new FunctionSkipObject());
        }
    }

    @Override
    public boolean toBoolean(NylonStack args) throws NylonRuntimeException {
        return args.pop().toBoolean();
    }

}
