package nylon.functions.misc;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonStack;
import nylon.objects.NylonString;

import java.util.Scanner;

/**
 * Created by Aedan Smith.
 */

public class RequestInput extends NylonFunction {

    private Scanner in = new Scanner(System.in);

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        returnStack.add(new NylonString(in.nextLine()));
    }

}
