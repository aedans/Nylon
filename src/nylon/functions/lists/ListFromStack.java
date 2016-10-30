package nylon.functions.lists;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class ListFromStack extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        returnStack.add(args.clone());
        args.clear();
    }

}
