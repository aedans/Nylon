package nylon.parser.parsers;

import nylon.nylonobjects.NylonFunction;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;
import nylon.parser.ParseUtils;
import nylon.parser.Parser;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public class ExternalFunctionParser {
    public static void addTo(ArrayList<Parser> parsers) {
        for (int i = 'a'; i <= 'z'; i++) {
            parsers.set(i, ExternalFunctionParser::parse);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            parsers.set(i, ExternalFunctionParser::parse);
        }
    }

    public static NylonFunction parse(CharIterator in, NylonParser nylonParser) {
        String name = ParseUtils.parseNextName(in);

        Supplier<NylonFunction> function = nylonParser.functions.get(name);
        if (function == null)
            throw new RuntimeException("Could not find function with name \"" + name + "\"");

        return function.get();
    }
}
