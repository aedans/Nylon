package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonString;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class StringParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '"')
            return false;
        in.skip();

        String s = "";
        while (in.hasNext() && in.peek() != '"') {
            if (in.peek() == '\\') {
                in.skip();
                s += in.next();
            } else {
                s += in.next();
            }
        }
        in.skip();

        char[] finalS = s.toCharArray();
        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                stack.add(new NylonString(finalS));
                return stack.peek();
            }

            @Override
            public String toString() {
                return "PushNylonString(\"" + new String(finalS) + "\")";
            }
        });

        return true;
    }
}
