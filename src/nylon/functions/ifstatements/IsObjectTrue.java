package nylon.functions.ifstatements;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.FunctionSkipObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class IsObjectTrue extends Conditional {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (toBoolean()) {
            returnStack.add(new FunctionSkipObject());
        }
    }

    @Override
    public boolean toBoolean(NylonStack args) throws NylonRuntimeException {
        return !args.pop().toBoolean();
    }

}
