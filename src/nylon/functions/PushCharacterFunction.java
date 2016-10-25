package nylon.functions;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonObject;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

class PushCharacterFunction extends NylonFunction {

    private char c;

    PushCharacterFunction(char c) {
        super(0);
        this.c = c;
    }

    @Override
    protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException {
        returnStack.add(new NylonCharacter(c));
    }

}
