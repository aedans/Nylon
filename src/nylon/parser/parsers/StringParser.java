package nylon.parser.parsers;

import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonString;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public class StringParser {
    public static void addTo(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
        parsers.set('"', StringParser::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        in.skip();

        String s = "";
        while (in.hasNext() && in.peek() != '"') {
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
