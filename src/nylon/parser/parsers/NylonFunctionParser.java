package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.Parser;
import nylon.parser.StringIterator;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class NylonFunctionParser {
    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set('[', NylonFunctionParser::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        in.skip();

        InlineFunction function = new InlineFunction("LambdaFunction");
        nylonParser.parseUntil(function, in, ']');

        in.skip();
        return function;
    }
}
