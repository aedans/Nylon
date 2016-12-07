package nylon;

import nylon.nylonobjects.NylonFunction;

/**
 * Created by Aedan Smith.
 */

public abstract class FunctionWrapper implements NylonFunction {
    protected NylonFunction wrappedFunction;

    protected FunctionWrapper(NylonFunction nylonFunction) {
        this.wrappedFunction = nylonFunction;
    }
}
