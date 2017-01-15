package nylon.parser.parsers;

import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.ParseUtils;
import nylon.parser.Parser;
import nylon.parser.StringIterator;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class MacroParser {
    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set('#', MacroParser::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        in.skip();

        String name = ParseUtils.parseNextName(in);

        NylonFunction nylonFunction = nylonParser.parse(in);

        nylonParser.functions.put(name, () -> nylonFunction);

        return null;
    }
}
