package nylon.functions.loops;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.*;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class WhileLoop extends NylonFunction {

    private NylonFunction function;

    public WhileLoop(NylonFunction function) {
        super(-2);
        this.function = function;
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        if (args.size() == 0)
            return;
        if (args.getLast() instanceof NylonFunction){
            NylonFunction conditional = (NylonFunction) args.removeLast();
            while (true){
                LinkedList<NylonObject> functionStack = conditional.apply((LinkedList<NylonObject>) args.clone());
                if (functionStack.size() != 0 && functionStack.getLast().getClass() == FunctionSkipObject.class)
                    break;
                returnStack.addAll(function.apply(args));
            }
        } else {
            double d = args.removeLast().toDouble();
            for (int i = 0; i < d; i++) {
                args = new LinkedList<>();
                args.add(new NylonDouble(i));
                returnStack.addAll(function.apply(args));
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "[" + function.toString() + "]";
    }

}
