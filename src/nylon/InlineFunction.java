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

    public InlineFunction() {
    }

    public InlineFunction(NylonFunction... functions) {
        Collections.addAll(this.functions, functions);
    }

    @Override
    public void apply(Stack<NylonObject> stack) {
        for (NylonFunction function : functions) {
            function.apply(stack);
        }
    }

    @Override
    public String toString() {
        return format("InlineFunction" + functions.toString());
    }
}
