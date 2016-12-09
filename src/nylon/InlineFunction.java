package nylon;

import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonList;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class InlineFunction extends NylonFunction {
    public ArrayList<NylonFunction> functions = new ArrayList<>();

    @Override
    public NylonObject apply(Stack<NylonObject> stack) {
        NylonList list = new NylonList();
        for (NylonFunction function : functions) {
            list.add(function.apply(stack));
        }
        return list;
    }

    @Override
    public String toString() {
        return "InlineFunction(" + functions.toString() + ")";
    }
}
