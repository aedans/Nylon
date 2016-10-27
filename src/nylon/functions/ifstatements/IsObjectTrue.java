package nylon.functions.ifstatements;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.FunctionSkipObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class IsObjectTrue extends Conditional {

    public IsObjectTrue() {
        super(1);
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (args.size() == 1){
            if (!args.getFirst().toBoolean())
                returnStack.add(new FunctionSkipObject());
        }
    }

    @Override
    public boolean toBoolean(NylonStack args) throws NylonRuntimeException {
        return args.size() != 1 || !args.getFirst().toBoolean();
    }

}
