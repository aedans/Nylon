package nylon.functions.misc;

import nylon.exceptions.InvalidArgumentException;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Cast extends NylonFunction {

    public Cast() {
        super(2);
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 2) {
            returnStack.add(args.lastElement().cast(args.firstElement().toChar()));
        } else {
            throw new InvalidArgumentException(this);
        }
    }

}
