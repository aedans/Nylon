package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

/**
 * Created by Aedan Smith.
 */

public class NylonBoolean extends NylonObject<Boolean> {
    public NylonBoolean(boolean b) {
        super(b, Type.BOOL, "Boolean");
    }

    @Override
    public double toDouble() {
        return this.value ? 1 : 0;
    }

    @Override
    public boolean toBoolean() throws NylonException {
        return this.value;
    }
}
