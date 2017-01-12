package nylon.parser.parsers;

import nylon.builtins.objects.BuiltinFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;

import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public class BuiltinParser {
    public static BiFunction<StringIterator, NylonParser, NylonFunction> getParser(BuiltinFunction builtinFunction) {
        return (stringIterator, nylonParser) -> {
            stringIterator.skip();
            return builtinFunction;
        };
    }
}
