package nylon.parser.parsers;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;
import nylon.parser.Parser;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class IfStatementParser {
    public static final char
            TRUE = '?',
            FALSE = '¿',
            GREATER = '>',
            LESS = '<',
            EQUALS = '=',
            ELSE = '!';

    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set(TRUE, IfStatementParser::parse);
        parsers.set(FALSE, IfStatementParser::parse);
        parsers.set(GREATER, IfStatementParser::parse);
        parsers.set(LESS, IfStatementParser::parse);
        parsers.set(EQUALS, IfStatementParser::parse);
    }

    public static NylonFunction parse(CharIterator in, NylonParser nylonParser) {
        char[] ifs = in.until(stringIterator -> in.hasNext() &&
                (in.peek() == TRUE
                        || in.peek() == FALSE
                        || in.peek() == LESS
                        || in.peek() == GREATER
                        || in.peek() == EQUALS
                        || in.peek() == ELSE
                )
        ).toCharArray();

        NylonFunction ifTrue = nylonParser.parse(in);

        in.skipWhitespace();
        NylonFunction ifFalse = new NylonFunction("EmptyFunction") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {

            }

            @Override
            public String toString() {
                return "EmptyFunction";
            }
        };
        if (in.hasNext() && in.peek() == ELSE) {
            in.skip();
            ifFalse = nylonParser.parse(in);
        }

        boolean pop = ifs[ifs.length - 1] != ELSE;
        NylonFunction finalIfFalse = ifFalse;
        return new NylonFunction("IfStatement(" + new String(ifs) + ")") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                boolean b = false;
                for (char c : ifs) {
                    switch (c) {
                        case TRUE:
                            b = stack.peek().toBoolean();
                            break;
                        case FALSE:
                            b = !stack.peek().toBoolean();
                            break;
                        case GREATER: {
                            NylonObject o1 = stack.peek(), o2 = stack.get(stack.size() - 2);
                            b = o2.toDouble() > o1.toDouble();
                            break;
                        }
                        case LESS: {
                            NylonObject o1 = stack.peek(), o2 = stack.get(stack.size() - 2);
                            b = o2.toDouble() < o1.toDouble();
                            break;
                        }
                        case EQUALS: {
                            NylonObject o1 = stack.peek(), o2 = stack.get(stack.size() - 2);
                            b = Objects.equals(o1.value, o2.value);
                            break;
                        }
                    }
                    if (b)
                        break;
                }
                if (pop)
                    stack.pop();
                if (b) {
                    ifTrue.apply(stack);
                } else {
                    finalIfFalse.apply(stack);
                }
            }

            @Override
            public String toString() {
                return "If[" + new String(ifs) + "]Then[" + ifTrue + "]Else[" + finalIfFalse + "]";
            }
        };
    }
}
