package nylon.functions.ifstatements;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public abstract class Conditional extends NylonFunction {

    public Conditional(int args) {
        super(args);
    }

    public abstract boolean toBoolean(NylonStack args) throws NylonRuntimeException;

    @Override
    public Conditional clone() {
        return (Conditional) super.clone();
    }

}
