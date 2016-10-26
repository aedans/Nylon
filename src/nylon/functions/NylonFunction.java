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
        if (args >= 0) {
            LinkedList<NylonObject> returnStack = new LinkedList<>();
            if (superStack.size() >= args) {
                LinkedList<NylonObject> args = new LinkedList<>();
                for (int i = 0; i < this.args; i++) {
                    args.add(superStack.removeLast());
                }
                this.applyImpl(args, returnStack);
            } else {
                LinkedList<NylonObject> args = (LinkedList<NylonObject>) superStack.clone();
                superStack.clear();
                this.applyImpl(args, returnStack);
            }
            return returnStack;
        } else if (args == -1) {
            superStack.clear();
            LinkedList<NylonObject> returnStack = new LinkedList<>();
            this.applyImpl((LinkedList<NylonObject>) superStack.clone(), returnStack);
            return returnStack;
        } else {
            LinkedList<NylonObject> returnStack = new LinkedList<>();
            this.applyImpl(superStack, returnStack);
            return returnStack;
        }
    }

    protected abstract void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
