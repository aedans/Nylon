package nylon.parser.parsers;

import nylon.NylonException;
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

public class WhileNotEmptyLoop {
    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set('&', WhileNotEmptyLoop::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        in.skip();

        NylonFunction wrapped = nylonParser.parse(in);

        return new NylonFunction("WhileNotEmpty(" + wrapped.getId() + ")") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                while (!stack.isEmpty()) {
                    wrapped.apply(stack);
                }
            }
        };
    }
}
