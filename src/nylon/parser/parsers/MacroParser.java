package nylon.parser.parsers;

import nylon.nylonobjects.NylonFunction;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;
import nylon.parser.ParseUtils;
import nylon.parser.Parser;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class MacroParser {
    public static final char DEFINE_MACRO = '#';

    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set(DEFINE_MACRO, MacroParser::parse);
    }

    public static NylonFunction parse(CharIterator in, NylonParser nylonParser) {
        in.skip();

        String name = ParseUtils.parseNextName(in);

        NylonFunction nylonFunction = nylonParser.parse(in);

        nylonParser.functions.put(name, () -> nylonFunction);

        return null;
    }
}
