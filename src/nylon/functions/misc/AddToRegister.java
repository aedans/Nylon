package nylon.functions.misc;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class AddToRegister extends NylonFunction {

    private static NylonObject[] registers = new NylonObject[6];

    private int register;

    public AddToRegister(int register) {
        this.register = register;
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() != 0 && registers[register] == null) {
            registers[register] = args.pop();
        } else {
            returnStack.add(registers[register]);
        }
    }
}
