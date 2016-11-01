package nylon.functions;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.functions.ifstatements.GreaterThan;
import nylon.functions.ifstatements.IsObjectFalse;
import nylon.functions.ifstatements.IsObjectTrue;
import nylon.functions.ifstatements.LessThan;
import nylon.functions.lists.CreateList;
import nylon.functions.lists.ListFromStack;
import nylon.functions.lists.Merge;
import nylon.functions.math.*;
import nylon.functions.misc.*;
import nylon.functions.variables.AddVariable;
import nylon.functions.variables.CallFunction;
import nylon.functions.variables.GetVariable;
import nylon.objects.NylonDouble;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;

import java.util.HashMap;

/**
 * Created by Aedan Smith.
 */

public class FunctionDictionary {

    public final HashMap<Character, NylonFunction> functionHashMap = new HashMap<>();

    public FunctionDictionary(NylonRuntime runtime){
        functionHashMap.put('#', new GetLibraryFunction(runtime));
        functionHashMap.put('$', new AddVariable(runtime));
        functionHashMap.put('&', new GetVariable(runtime));
        functionHashMap.put('ƒ', new CallFunction(runtime));

        functionHashMap.put('+', new Add());
        functionHashMap.put('-', new Subtract());
        functionHashMap.put('*', new Multiply());
        functionHashMap.put('/', new Divide());
        functionHashMap.put('%', new Modulo());
        functionHashMap.put('^', new PowerOf());

        functionHashMap.put('?', new IsObjectTrue());
        functionHashMap.put('¿', new IsObjectFalse());
        functionHashMap.put('>', new GreaterThan());
        functionHashMap.put('<', new LessThan());

        functionHashMap.put('†', new CreateList());
        functionHashMap.put('‡', new ListFromStack());
        functionHashMap.put('«', new Merge());

        functionHashMap.put('Ç', new Cast());
        functionHashMap.put('š', new RequestInput());
        functionHashMap.put(':', new PopTop());
        functionHashMap.put(';', new Clone());
        functionHashMap.put('µ', new NylonFunction() {
            @Override
            protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
                System.exit(0);
            }
        });
        functionHashMap.put('ð', new PushNylonObjectFunction<>(new NylonDouble(10)));
        functionHashMap.put('Ð', new PushNylonObjectFunction<>(new NylonDouble(1000)));
        for (char c = 'à'; c <= 'æ'; c++) {
            functionHashMap.put(c, new AddToRegister(c - 'à'));
        }

        functionHashMap.put(' ', null);
        functionHashMap.put('\n', null);
    }

}
