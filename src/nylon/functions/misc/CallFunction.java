package nylon.functions.misc;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class CallFunction extends NylonFunction {

    private NylonRuntime nylonRuntime;

    public CallFunction(NylonRuntime nylonRuntime){
        super(-2);
        this.nylonRuntime = nylonRuntime;
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        returnStack.addAll(nylonRuntime.getFunction(args.pop().toInteger()).apply(args));
    }

}
