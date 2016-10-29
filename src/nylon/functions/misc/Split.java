package nylon.functions.misc;

import nylon.exceptions.NullArgumentException;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Split extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() > 0)
            for (char c : args.pop().toString().toCharArray()){
                returnStack.add(new NylonCharacter(c));
            }
        else
            throw new NullArgumentException(this);
    }

}
