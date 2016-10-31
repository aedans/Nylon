package nylon.functions.loops;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

import java.util.Iterator;

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

        boolean reversed = false, skip = false;
        while (args.size() != 0 && args.peek() instanceof NylonCharacter) {
            if (args.peek().toChar() == 'I') {
                reversed = true;
                args.pop();
            } else if (args.peek().toChar() == 'P') {
                skip = true;
                args.pop();
            } else
                break;
        }

        Iterator<NylonObject> iterator = reversed ? args.pop().reverseIterator(args) : args.pop().iterator(args);
        if (skip) iterator.next();
        while (iterator.hasNext()) {
            returnStack.addAll(function.apply(new NylonStack(iterator.next())));
        }
    }

    @Override
    public String toString() {
        return super.toString() + "[" + function.toString() + "]";
    }

}
