package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.*;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class CastParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '~')
            return false;
        in.skip();

        char c = in.next();

        inlineFunction.functions.add(new NylonFunction() {
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
                }
            }

            @Override
            public String toString() {
                return "CastToObject('" + c + "')";
            }
        });
        return true;
    }
}
