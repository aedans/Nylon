package nylon.functions;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.FunctionSkip;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public abstract class NylonFunction {

    private int args;

    public NylonFunction(int args){
        this.args = args;
    }

    @SuppressWarnings("unchecked")
    public LinkedList<NylonObject> apply(LinkedList<NylonObject> superStack) throws NylonRuntimeException {
        if (superStack.size() != 0 && superStack.getLast().getClass() == FunctionSkip.class) {
            superStack.removeLast();
            return superStack;
        }
        LinkedList<NylonObject> functionStack = new LinkedList<>();
        if (args >= 0) {
            if (superStack.size() >= args) {
                LinkedList<NylonObject> args = new LinkedList<>();
                for (int i = 0; i < this.args; i++) {
                    args.add(superStack.removeLast());
                }
                this.applyImpl(args, functionStack);
            } else {
                LinkedList<NylonObject> args = (LinkedList<NylonObject>) superStack.clone();
                superStack.clear();
                this.applyImpl(args, functionStack);
            }
        } else if (args == -1) {
            superStack.clear();
            this.applyImpl((LinkedList<NylonObject>) superStack.clone(), functionStack);
        } else {
            this.applyImpl(superStack, functionStack);
        }
        LinkedList<NylonObject> returnStack = new LinkedList<>();
        if (functionStack.size() != 0) {
            Class clazz = functionStack.getLast().getClass();
            NylonObject object;
            while (functionStack.size() != 0) {
                object = functionStack.removeLast();
                if (object.getClass() == clazz)
                    returnStack.add(object);
                else
                    break;
            }
        }
        return returnStack;
    }

    protected abstract void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
