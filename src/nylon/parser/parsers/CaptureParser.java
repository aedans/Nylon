package nylon.parser.parsers;

import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.Parser;
import nylon.parser.StringIterator;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class CaptureParser {
    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set('@', CaptureParser::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        in.skip();

        NylonFunction capture = nylonParser.parse(in);
        return new NylonFunction("Capture(" + capture.getId() + ")") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                stack.add(capture);
            }

            @Override
            public String toString() {
                return format("Capture[" + capture + "]");
            }
        };
    }
}
