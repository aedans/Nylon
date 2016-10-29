package nylon.functions.variables;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class AddVariable extends NylonFunction {

    private NylonRuntime nylonRuntime;

    public AddVariable(NylonRuntime nylonRuntime) {
        this.nylonRuntime = nylonRuntime;
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            returnStack.add(new NylonCharacter('$'));
        if (args.size() == 1)
            nylonRuntime.addVariable("t", args.pop());
        else
            nylonRuntime.addVariable(args.pop().toString(), args.pop());
    }

}
