package nylon.parser;

import nylon.nylonobjects.NylonFunction;

import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public interface Parser extends BiFunction<StringIterator, NylonParser, NylonFunction> {
}
