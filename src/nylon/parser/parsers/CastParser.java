package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonCharacter;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonFunction;
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
            public NylonObject apply(Stack<NylonObject> stack) {
                switch (c) {
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
                        stack.add(stack.pop().toList(stack));
                        break;
                    case 's':
                        stack.add(stack.pop().toNylonString(stack));
                        break;
                }
                return stack.peek();
            }

            @Override
            public String toString() {
                return "CastToObject('" + c + "')";
            }
        });
        return true;
    }
}
