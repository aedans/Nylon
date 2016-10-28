package nylon.functions;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

class CallFunction extends NylonFunction {

    private NylonRuntime nylonRuntime;

    CallFunction(NylonRuntime nylonRuntime){
        super(-2);
        this.nylonRuntime = nylonRuntime;
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        returnStack.addAll(nylonRuntime.getFunction(args.pop().toInteger()).apply(args));
    }

}
