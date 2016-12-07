package nylon;

import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonStack;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class InlineFunction implements NylonFunction {
    public ArrayList<NylonFunction> functions = new ArrayList<>();

    @Override
    public NylonStack apply(NylonStack stack) {
        NylonStack returnStack = new NylonStack();
        for (NylonFunction function : functions) {
            returnStack.add(function.apply(stack));
        }
        return returnStack;
    }

    @Override
    public String toString() {
        return "InlineFunction(" + functions.toString() + ")";
    }
}
