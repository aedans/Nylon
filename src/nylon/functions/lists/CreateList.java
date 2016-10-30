package nylon.functions.lists;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class CreateList extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() != 0 && args.peek() instanceof NylonFunction) {
            returnStack.add(((NylonFunction) args.pop()).apply(args));
        } else {
            returnStack.add(new NylonStack());
        }
    }

}
