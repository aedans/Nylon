package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.*;

/**
 * Created by Aedan Smith.
 */

public class PowerOf extends NylonFunction {

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0 || args.size() == 1)
            returnStack.add(new NylonCharacter('^'));
        else {
            NylonObject[] vars = args.pop(2);
            returnStack.add(new NylonDouble(Math.pow(vars[0].toDouble(), vars[1].toDouble())));
        }
    }

}
