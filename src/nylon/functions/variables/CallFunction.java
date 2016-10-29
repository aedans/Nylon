package nylon.functions.variables;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.exceptions.UnconvertableTypeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class CallFunction extends NylonFunction {

    private NylonRuntime runtime;

    public CallFunction(NylonRuntime runtime) {
        this.runtime = runtime;
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            returnStack.push(new NylonCharacter('ƒ'));
        else {
            NylonObject object = runtime.getVariable(args.pop().toString());
            if (object instanceof NylonFunction) {
                returnStack.addAll(((NylonFunction) object).apply(args));
            } else {
                throw new UnconvertableTypeException(object, NylonFunction.class);
            }
        }
    }

}
