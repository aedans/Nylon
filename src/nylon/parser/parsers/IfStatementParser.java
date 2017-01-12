package nylon.parser.parsers;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.EmptyFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public class IfStatementParser {
    public static void addTo(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
        parsers.set('?', IfStatementParser::parse);
        parsers.set('¿', IfStatementParser::parse);
        parsers.set('>', IfStatementParser::parse);
        parsers.set('<', IfStatementParser::parse);
        parsers.set('=', IfStatementParser::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        char[] ifs = in.until(stringIterator -> in.hasNext() &&
                (in.peek() == '?'
                        || in.peek() == '¿'
                        || in.peek() == '>'
                        || in.peek() == '<'
                        || in.peek() == '='
                        || in.peek() == '!'
                )
        ).toCharArray();

        NylonFunction ifTrue = nylonParser.parse(in);

        in.skipWhitespace();
        NylonFunction ifFalse = new EmptyFunction();
        if (in.hasNext() && in.peek() == '!') {
            in.skip();
            ifFalse = nylonParser.parse(in);
        }

        boolean pop = ifs[ifs.length - 1] != '!';
        NylonFunction finalIfFalse = ifFalse;
        return new NylonFunction("IfStatement") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                boolean b = false;
                for (char c : ifs) {
                    switch (c) {
                        case '?':
                            b = stack.peek().toBoolean(stack);
                            break;
                        case '¿':
                            b = !stack.peek().toBoolean(stack);
                            break;
                        case '>': {
                            NylonObject o1 = stack.peek(), o2 = stack.get(stack.size() - 2);
                            b = o2.toDouble(stack) > o1.toDouble(stack);
                            break;
                        }
                        case '<': {
                            NylonObject o1 = stack.peek(), o2 = stack.get(stack.size() - 2);
                            b = o2.toDouble(stack) < o1.toDouble(stack);
                            break;
                        }
                        case '=': {
                            NylonObject o1 = stack.peek(), o2 = stack.get(stack.size() - 2);
                            b = Objects.equals(o1, o2);
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
