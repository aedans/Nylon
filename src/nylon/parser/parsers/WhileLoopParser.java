package nylon.parser.parsers;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;
import nylon.parser.Parser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class WhileLoopParser {
    public static final char WHILE_LOOP = '&';

    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set(WHILE_LOOP, WhileLoopParser::parse);
    }

    public static NylonFunction parse(CharIterator in, NylonParser nylonParser) {
        in.skip();

        NylonFunction test = nylonParser.parse(in);
        NylonFunction wrapped = nylonParser.parse(in);

        return new NylonFunction("While(" + test.getId() + ")Then(" + wrapped.getId() + ")") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                while (true) {
                    test.apply(stack);
                    if (!stack.pop().toBoolean())
                        break;
                    else
                        wrapped.apply(stack);
                }
            }

            @Override
            public String toString() {
                return "While[" + test + "]Then[" + wrapped + "]";
            }
        };
    }
}
