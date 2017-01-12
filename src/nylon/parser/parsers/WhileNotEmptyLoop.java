package nylon.parser.parsers;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public class WhileNotEmptyLoop {
    public static void addTo(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
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
