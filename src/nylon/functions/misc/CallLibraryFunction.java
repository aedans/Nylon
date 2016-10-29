package nylon.functions.misc;

import nylon.NylonRuntime;
import nylon.exceptions.NullArgumentException;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class CallLibraryFunction extends NylonFunction {

    private NylonRuntime nylonRuntime;

    public CallLibraryFunction(NylonRuntime nylonRuntime) {
        this.nylonRuntime = nylonRuntime;
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() > 0)
            returnStack.add(nylonRuntime.getFunction(args.pop().toString()));
        else
            throw new NullArgumentException(this);
    }

}
