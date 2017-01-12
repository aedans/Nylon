package nylon.parser.parsers;

import nylon.NylonObject;
import nylon.nylonobjects.NylonCharacter;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public class CharacterParser {
    public static void addTo(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
        parsers.set('\'', CharacterParser::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser parser) {
        in.skip();

        char c = in.next();

        return new NylonFunction("PushNylonCharacter('" + (c == '\n' ? "\\n" : c) + "')") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                NylonCharacter nc = new NylonCharacter(c);
                stack.push(nc);
            }

            @Override
            public String toString() {
                return id;
            }
        };
    }
}
