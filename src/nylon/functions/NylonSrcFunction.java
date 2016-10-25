package nylon.functions;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.objects.FunctionSkip;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class NylonSrcFunction extends NylonFunction {

    private LinkedList<NylonFunction> functions = new LinkedList<>();

    public NylonSrcFunction(NylonRuntime nylonRuntime, String src) throws NylonRuntimeException {
        super(0); // TODO Allow modifiable arguments
        for (int i = 0; i < src.length(); i++) {
            if (Character.isDigit(src.charAt(i))){
                int j = i;
                for (; i < src.length(); i++) {
                    if (!Character.isDigit(src.charAt(i)))
                        break;
                }
                functions.add(new PushIntegerFunction(Integer.parseInt(src.substring(j, i))));
                i--;
                continue;
            }
            // If the character is [a-zA-Z]
            if ((src.charAt(i) >= 97 && src.charAt(i) <= 122) || (src.charAt(i) >= 65 && src.charAt(i) <= 90)){
                int j = i;
                for (; i < src.length(); i++) {
                    if (!Character.isAlphabetic(src.charAt(i)))
                        break;
                }
                if (i == j+1)
                    functions.add(new PushCharacterFunction(src.charAt(j)));
                else
                    functions.add(new PushStringFunction(src.substring(j, i)));
                i--;
                continue;
            }
            functions.add(FunctionDictionary.get(nylonRuntime, src.charAt(i)));
        }
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        returnStack.addAll(args);
        for (NylonFunction nylonFunction : functions){
            if (returnStack.size() == 0 || returnStack.getLast().getClass() != FunctionSkip.class) {
                returnStack.addAll(nylonFunction.apply(returnStack));
            } else {
                returnStack.removeLast();
            }
        }
    }
}
