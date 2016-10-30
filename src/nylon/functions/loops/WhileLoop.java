package nylon.functions.loops;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.ifstatements.Conditional;
import nylon.objects.*;

import java.util.ArrayList;

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
        if (args.peek() instanceof Conditional){
            Conditional conditional = (Conditional) args.pop();
            while (conditional.toBoolean(args)) {
                returnStack.addAll(function.apply(args));
            }
        } else if (args.peek() instanceof NylonStack) {
            NylonStack stack = ((NylonStack) args.pop());
            for (NylonObject object : stack) {
                returnStack.addAll(function.apply(new NylonStack(object)));
            }
        } else {
            double d = args.pop().toDouble();
            ArrayList<Character> mods = new ArrayList<>();
            while (args.size() != 0 && args.peek() instanceof NylonCharacter){
                char c = ((NylonCharacter) args.peek()).getValue();
                if (c == 'I' || c == 'P') {
                    mods.add(c);
                    args.pop();
                }
                else break;
            }
            args = args.clone();
            if (!mods.contains('I')) {
                for (int i = mods.contains('P') ? 1 : 0; i < d; i++) {
                    args.add(new NylonDouble(i));
                    returnStack.addAll(function.apply(args));
                }
            } else {
                for (int i = (int) d; i >= 0; i--) {
                    args.add(new NylonDouble(i));
                    returnStack.addAll(function.apply(args));
                }
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "[" + function.toString() + "]";
    }

}
