package nylon.functions.loops;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.PushNylonObjectFunction;
import nylon.functions.ifstatements.Conditional;
import nylon.objects.*;

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
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        if (args.size() == 0)
            return;
        if (args.getLast() instanceof Conditional){
            Conditional conditional = (Conditional) args.removeLast();
            while (conditional.toBoolean(args)) {
                returnStack.addAll(function.apply(args));
            }
        } else {
            double d = args.removeLast().toDouble();
            for (int i = 0; i < d; i++) {
                args.add(new NylonDouble(i));
                returnStack.addAll(function.apply(args.clone()));
                args.removeLast();
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "[" + function.toString() + "]";
    }

}