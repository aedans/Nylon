package nylon.parser.parsers;

import nylon.builtins.objects.BuiltinFunction;
import nylon.parser.Parser;

/**
 * Created by Aedan Smith.
 */

public class BuiltinParser {
    public static Parser getParser(BuiltinFunction builtinFunction) {
        return (stringIterator, nylonParser) -> {
            stringIterator.skip();
            return builtinFunction;
        };
    }
}
