package nylon.functions.variables;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class GetVariable extends NylonFunction {

    private NylonRuntime nylonRuntime;

    public GetVariable(NylonRuntime nylonRuntime) {
        this.nylonRuntime = nylonRuntime;
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            returnStack.add(new NylonCharacter('&'));
        else
            returnStack.push(nylonRuntime.getVariable(args.pop().toString()));
    }

}
