package nylon.functions.loops;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.ifstatements.Conditional;
import nylon.objects.*;

/**
 * Created by Aedan Smith.
 */

public class WhileLoop extends NylonFunction {

    private NylonFunction function;

    public WhileLoop(NylonFunction function) {
        this.function = function;
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (args.size() == 0)
            return;
        if (args.lastElement() instanceof Conditional){
            Conditional conditional = (Conditional) args.pop();
            while (conditional.toBoolean(args)) {
                returnStack.addAll(function.apply(args));
            }
        } else {
            double d = args.pop().toDouble();
            for (int i = 0; i < d; i++) {
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
