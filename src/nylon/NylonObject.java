package nylon;

import nylon.nylonobjects.NylonStack;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public interface NylonObject {
    boolean toBoolean(NylonStack stack);

    double toDouble(NylonStack stack);

    Iterator<NylonObject> toIterator(NylonStack stack);

    NylonStack toStack(NylonStack stack);
}

