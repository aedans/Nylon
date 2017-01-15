package nylon.parser.parsers;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.*;
import nylon.parser.NylonParser;
import nylon.parser.Parser;
import nylon.parser.StringIterator;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class CastParser {
    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set('~', CastParser::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser parser) {
        in.skip();

        char c = in.next();

        // TODO Be decent
        return new NylonFunction("CastToObject('" + c + "')") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                switch (c) {
                    case 'a':
                        stack.add(stack.pop().toArray(stack));
                        break;
                    case 'b':
                        stack.add(new NylonBoolean(stack.pop().toBoolean(stack)));
                        break;
                    case 'c':
                        stack.add(new NylonCharacter(stack.pop().toCharacter(stack)));
                        break;
                    case 'd':
                        stack.add(new NylonDouble(stack.pop().toDouble(stack)));
                        break;
                    case 'f':
                        stack.add(stack.pop().toFunction(stack));
                        break;
                    case 'l':
                        stack.add(new NylonLong(stack.pop().toLong(stack)));
                        break;
                    case 's':
                        stack.add(stack.pop().toNylonString(stack));
                        break;
                    default:
                        throw new NylonException("Cannot cast object to '" + c + "'", this);
                }
            }

            @Override
            public String toString() {
                return id;
            }
        };
    }
}
