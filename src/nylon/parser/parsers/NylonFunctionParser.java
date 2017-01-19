package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;
import nylon.parser.Parser;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class NylonFunctionParser {
    public static final char BEGIN_FUNCTION = '{', END_FUNCTION = '}';

    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set(BEGIN_FUNCTION, NylonFunctionParser::parse);
    }

    public static NylonFunction parse(CharIterator in, NylonParser nylonParser) {
        in.skip();

        InlineFunction function = new InlineFunction("LambdaFunction");
        nylonParser.parseUntil(function, in, END_FUNCTION);

        in.skip();
        return function;
    }
}
