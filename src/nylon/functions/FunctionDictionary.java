package nylon.functions;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.functions.ifstatements.IsObjectFalse;
import nylon.functions.ifstatements.IsObjectTrue;
import nylon.functions.math.*;
import nylon.functions.misc.*;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

import java.util.HashMap;

/**
 * Created by Aedan Smith.
 */

public class FunctionDictionary {

    public final HashMap<Character, NylonFunction> functionHashMap = new HashMap<>();

    public FunctionDictionary(NylonRuntime runtime){
        functionHashMap.put('ƒ', new CallFunction(runtime));
        functionHashMap.put('#', new CallLibraryFunction(runtime));

        functionHashMap.put('+', new Add());
        functionHashMap.put('-', new Subtract());
        functionHashMap.put('*', new Multiply());
        functionHashMap.put('/', new Divide());
        functionHashMap.put('%', new Modulo());
        functionHashMap.put('^', new PowerOf());

        functionHashMap.put('?', new IsObjectTrue());
        functionHashMap.put('¿', new IsObjectFalse());

        functionHashMap.put('|', new Split());
        functionHashMap.put('Ç', new Cast());
        functionHashMap.put(':', new PopTop());
        functionHashMap.put('µ', new NylonFunction() {
            @Override
            protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
                System.exit(0);
            }
        });

        functionHashMap.put(' ', null);
        functionHashMap.put('\n', null);
    }

}
