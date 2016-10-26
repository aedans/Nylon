package nylon.functions;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.functions.ifstatements.IsObjectFalse;
import nylon.functions.ifstatements.IsObjectTrue;
import nylon.functions.math.*;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class FunctionDictionary {

    private HashMap<Character, NylonFunction> functionHashMap = new HashMap<>();

    {
        functionHashMap.put('+', new Add());
        functionHashMap.put('-', new Subtract());
        functionHashMap.put('*', new Multiply());
        functionHashMap.put('/', new Divide());
        functionHashMap.put('%', new Modulo());
        functionHashMap.put('^', new PowerOf());

        functionHashMap.put('?', new IsObjectTrue());
        functionHashMap.put('¿', new IsObjectFalse());

        functionHashMap.put(':', new PopTop());
    }

    public NylonFunction get(NylonRuntime runtime, char key) throws NylonRuntimeException {
        switch (key){
            case 'ƒ':
                return new CallFunction(runtime);
            case ' ':
                return null;
            default:
                NylonFunction nylonFunction = functionHashMap.get(key);
                if (nylonFunction != null)
                    return functionHashMap.get(key);
                else
                    throw new NylonRuntimeException("Could not find function with key '" + key + "'");
        }
    }

}
