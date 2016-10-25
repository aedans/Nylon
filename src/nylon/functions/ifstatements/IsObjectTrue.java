package nylon.functions.ifstatements;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.NylonFunction;
import nylon.objects.FunctionSkip;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class IsObjectTrue extends NylonFunction {

    public IsObjectTrue() {
        super(1);
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        if (args.size() == 1){
            if (!args.getFirst().toBoolean())
                returnStack.add(new FunctionSkip());
        }
    }

}
