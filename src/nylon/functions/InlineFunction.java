package nylon.functions;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;
import nylon.parser.FunctionParser;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class InlineFunction extends NylonFunction {

    private LinkedList<NylonFunction> functions = new LinkedList<>();
    private byte args = 0;

    public InlineFunction(NylonRuntime nylonRuntime, String src) throws NylonRuntimeException {
        if (src.length() != 0 && src.charAt(0) >= 48 && src.charAt(0) < 58) {
            this.args = (byte) (src.charAt(0) - 48);
            src = src.substring(1);
        }
        this.functions = FunctionParser.parse(nylonRuntime, src);
    }

    public InlineFunction(NylonRuntime nylonRuntime, String src, byte args) throws NylonRuntimeException {
        this.args = args;
        this.functions = FunctionParser.parse(nylonRuntime, src);
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        for (int i = 0; i < this.args && args.size() != 0; i++) {
            returnStack.add(args.pop());
        }
        for (NylonFunction function : functions) {
            returnStack.addAll(function.apply(returnStack));
        }
    }

    @Override
    public String toString() {
        return super.toString() + functions.toString();
    }

}
