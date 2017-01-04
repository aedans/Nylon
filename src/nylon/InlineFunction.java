package nylon;

import nylon.nylonobjects.NylonFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class InlineFunction extends NylonFunction {
    public ArrayList<NylonFunction> functions = new ArrayList<>();

    public InlineFunction(String id, NylonFunction... functions) {
        this.id = id;
        Collections.addAll(this.functions, functions);
    }

    @Override
    public void applyImpl(Stack<NylonObject> stack) throws NylonException {
        for (NylonFunction function : functions) {
            function.apply(stack);
        }
    }

    @Override
    public String toString() {
        if (functions != null) {
            return NylonFunction.format("InlineFunction" + functions.toString());
        } else {
            return "InlineFunction(null)";
        }
    }
}
