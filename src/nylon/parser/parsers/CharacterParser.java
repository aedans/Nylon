package nylon.parser.parsers;

import nylon.NylonObject;
import nylon.nylonobjects.NylonCharacter;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;
import nylon.parser.Parser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class CharacterParser {
    public static final char CHAR_ESCAPE = '\'';

    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set(CHAR_ESCAPE, CharacterParser::parse);
    }

    public static NylonFunction parse(CharIterator in, NylonParser parser) {
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
