package nylon.functions.lists;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Merge extends NylonFunction {
    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0 || args.size() == 1)
            returnStack.add(new NylonCharacter('«'));
        else {
            returnStack.add(args.pop());
            while (args.size() > 0) {
                returnStack.add(args.pop().concatenate(returnStack.pop()));
            }
        }
    }
}
