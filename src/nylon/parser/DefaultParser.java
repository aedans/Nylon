package nylon.parser;

import nylon.nylonobjects.NylonFunction;

import java.util.ArrayList;
import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

class DefaultParser {
    public static void addTo(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
        for (int i = 0; i < 256; i++) {
            parsers.add(i, DefaultParser::parse);
        }
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        in.skip();
        return null;
    }
}
