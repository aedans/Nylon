package nylon.functions.misc;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class PopTop extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (args.size() != 0) {
            if (args.peek() instanceof NylonFunction) {
                returnStack.addAll(((NylonFunction) args.pop()).apply(args));
            } else {
                System.out.println(args.pop());
            }
        } else {
            returnStack.add(new NylonCharacter(':'));
        }
    }

}