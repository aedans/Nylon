package nylon.parser.parsers;

import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonString;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;
import nylon.parser.Parser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class StringParser {
    public static char BEGIN_STRING = '"', END_STRING = '"';

    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set(BEGIN_STRING, StringParser::parse);
    }

    public static NylonFunction parse(CharIterator in, NylonParser nylonParser) {
        in.skip();

        String s = "";
        while (in.hasNext() && in.peek() != END_STRING) {
            if (in.peek() == '\\') {
                in.skip();
                s += in.next();
            } else {
                s += in.next();
            }
        }
        in.skip();

        char[] finalS = s.toCharArray();
        return new NylonFunction("PushNylonString(\"" + new String(finalS).replaceAll("\\n", "\\\\n") + "\")") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                stack.add(new NylonString(finalS));
            }

            @Override
            public String toString() {
                return getId();
            }
        };
    }
}
