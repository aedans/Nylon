package nylon.parser.parsers;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonCharacter;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonLong;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;
import nylon.parser.Parser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class CastParser {
    public static final char
            TO_ARRAY = 'ä',
            TO_DOUBLE = 'à',
            TO_LONG = 'á',
            TO_CHAR = 'â',
            TO_STRING = 'ã',
            TO_FUNCTION = 'å';

    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set(TO_ARRAY, CastParser::parse);
        parsers.set(TO_DOUBLE, CastParser::parse);
        parsers.set(TO_LONG, CastParser::parse);
        parsers.set(TO_CHAR, CastParser::parse);
        parsers.set(TO_STRING, CastParser::parse);
        parsers.set(TO_FUNCTION, CastParser::parse);
    }

    public static NylonFunction parse(CharIterator in, NylonParser parser) {
        char c = in.next();

        Caster caster;

        switch (c) {
            case TO_ARRAY:
                caster = (nylonObject1, stack1) -> nylonObject1.toArray();
                break;
            case TO_DOUBLE:
                caster = (nylonObject, stack) -> new NylonDouble(nylonObject.toDouble());
                break;
            case TO_LONG:
                caster = (nylonObject, stack) -> new NylonLong(nylonObject.toLong());
                break;
            case TO_CHAR:
                caster = (nylonObject, stack) -> new NylonCharacter(nylonObject.toCharacter());
                break;
            case TO_STRING:
                caster = (nylonObject1, stack1) -> nylonObject1.toNylonString();
                break;
            case TO_FUNCTION:
                caster = (nylonObject, stack) -> nylonObject.toFunction();
                break;
            default:
                throw new RuntimeException("Could not create casting function of type '" + c + "'");
        }

        Caster finalCaster = caster;
        return new NylonFunction("CastToObject('" + c + "')") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.add(finalCaster.cast(stack.pop(), stack));
            }

            @Override
            public String toString() {
                return id;
            }
        };
    }

    private interface Caster {
        NylonObject cast(NylonObject nylonObject, Stack<NylonObject> stack) throws NylonException;
    }
}
