package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public interface NylonFunction extends NylonObject {
    NylonObject apply(NylonStack stack);

    default boolean toBoolean(NylonStack stack) {
        return apply(stack).toDouble(stack) != 0;
    }

    default double toDouble(NylonStack stack) {
        return apply(stack).toDouble(stack);
    }

    @Override
    default NylonStack toStack(NylonStack stack) {
        return apply(stack).toStack(stack);
    }

    @Override
    default Iterator<NylonObject> toIterator(NylonStack stack) {
        return apply(stack).toIterator(stack);
    }
}
