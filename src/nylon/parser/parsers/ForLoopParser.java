package nylon.parser.parsers;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;
import nylon.parser.Parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class ForLoopParser {
    public static final char
            PC_FOR = 'î',
            P_FOR = 'ì',
            C_FOR = 'í',
            _FOR = 'ï';

    public static void addTo(ArrayList<Parser> parsers) {
        // TODO separate methods
        parsers.set(PC_FOR, ForLoopParser::parse);
        parsers.set(P_FOR, ForLoopParser::parse);
        parsers.set(C_FOR, ForLoopParser::parse);
        parsers.set(_FOR, ForLoopParser::parse);
    }

    public static NylonFunction parse(CharIterator in, NylonParser nylonParser) {
        boolean push = in.peek() == PC_FOR || in.peek() == P_FOR;
        boolean consume = in.peek() == PC_FOR || in.peek() == C_FOR;
        in.skip();

        NylonFunction wrapped = nylonParser.parse(in);

        return new NylonFunction("ForLoop(" + wrapped.getId() + ")") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                Iterator<NylonObject> iterator = consume ? stack.pop().toIterator() : stack.peek().toIterator();
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
