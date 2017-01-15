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
        super(id);
        Collections.addAll(this.functions, functions);
    }

    @Override
    public void applyImpl(Stack<NylonObject> stack) throws NylonException {
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0, functionsSize = functions.size(); i < functionsSize; i++) {
            functions.get(i).apply(stack);
        }
    }

    @Override
    public String toString() {
        if (functions != null) {
            return NylonFunction.format("InlineFunction" + functions.toString());
        } else {
            return "InlineFunction()";
        }
    }
}
