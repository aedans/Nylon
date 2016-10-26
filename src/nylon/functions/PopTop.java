package nylon.functions;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class PopTop extends NylonFunction {

    public PopTop() {
        super(-2);
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        if (args.size() != 0) {
            if (args.getLast() instanceof NylonFunction) {
                returnStack.addAll(((NylonFunction) args.removeLast()).apply(args));
            } else {
                System.out.println(args.removeLast());
            }
        } else {
            returnStack.add(new NylonCharacter(':'));
        }
    }

}
