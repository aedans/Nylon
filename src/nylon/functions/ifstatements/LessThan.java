package nylon.functions.ifstatements;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class LessThan extends NylonFunction {
    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0 || args.size() == 1) {
            returnStack.add(new NylonCharacter('<'));
        } else {
            returnStack.add(args.pop().compareTo(args.pop(), args) == 1
                    ? new NylonCharacter('t')
                    : new NylonCharacter('f'));
        }
    }
}
