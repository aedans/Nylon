package nylon.functions;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

class CallFunction extends NylonFunction {

    private NylonRuntime nylonRuntime;

    CallFunction(NylonRuntime nylonRuntime){
        super((int) Float.NEGATIVE_INFINITY);
        this.nylonRuntime = nylonRuntime;
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        LinkedList<NylonObject> functionStack = new LinkedList<>();
        functionStack.addAll(nylonRuntime.getFunction((int) args.removeLast().toLong()).apply(args));
        Class clazz = functionStack.getLast().getClass();
        NylonObject object;
        while (functionStack.size() != 0){
            object = functionStack.removeLast();
            if (object.getClass() == clazz)
                returnStack.add(object);
            else
                break;
        }
    }

}
