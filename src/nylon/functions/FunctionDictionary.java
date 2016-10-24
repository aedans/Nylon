package nylon.functions;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.functions.math.*;
import nylon.objects.NylonObject;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public final class FunctionDictionary {

    private static HashMap<Character, NylonFunction> functionHashMap = new HashMap<>();

    static {
        functionHashMap.put('+', new Add());
        functionHashMap.put('-', new Subtract());
        functionHashMap.put('*', new Multiply());
        functionHashMap.put('/', new Divide());
        functionHashMap.put('%', new Modulo());
        functionHashMap.put('^', new PowerOf());
        functionHashMap.put(' ', new NylonFunction(0) {
            @Override
            protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
                    throws NylonRuntimeException {}
        });
    }

    public static NylonFunction get(NylonRuntime runtime, char key) throws NylonRuntimeException {
        switch (key){
            case 'ƒ':
                return new CallFunction(runtime);
            default:
                NylonFunction nylonFunction = functionHashMap.get(key);
                if (nylonFunction != null)
                    return functionHashMap.get(key);
                else
                    throw new NylonRuntimeException("Could not find function with key '" + key + "'");
        }
    }

}
