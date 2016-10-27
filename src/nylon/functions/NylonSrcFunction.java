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

public class NylonSrcFunction extends NylonFunction {

    private LinkedList<NylonFunction> functions = new LinkedList<>();

    public NylonSrcFunction(NylonRuntime nylonRuntime, String src, int args) throws NylonRuntimeException {
        super(args);
        functions = FunctionParser.parse(nylonRuntime, src);
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack)
            throws NylonRuntimeException {
        returnStack.addAll(args);
        for (NylonFunction function : functions) {
            returnStack.addAll(function.apply(returnStack));
        }
    }

    @Override
    public String toString() {
        return super.toString() + functions.toString();
    }

}
