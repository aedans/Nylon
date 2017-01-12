package nylon.parser.parsers;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public class ForLoopParser {
    public static void addTo(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
        // TODO separate methods
        parsers.set('î', ForLoopParser::parse);
        parsers.set('ì', ForLoopParser::parse);
        parsers.set('î', ForLoopParser::parse);
        parsers.set('í', ForLoopParser::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        boolean push = in.peek() == 'î' || in.peek() == 'ì';
        boolean consume = in.peek() == 'î' || in.peek() == 'í';
        in.skip();

        NylonFunction wrapped = nylonParser.parse(in);

        return new NylonFunction("ForLoop(" + wrapped.getId() + ")") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                Iterator<NylonObject> iterator = consume ? stack.pop().toIterator(stack) : stack.peek().toIterator(stack);
                while (iterator.hasNext()) {
                    if (push)
                        stack.push(iterator.next());
                    else
                        iterator.next();
                    wrapped.apply(stack);
                }
            }

            @Override
            public String toString() {
                return "ForLoop[" + wrapped + "]";
            }
        };
    }
}
