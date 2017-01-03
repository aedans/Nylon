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
            } else if (in.peek() == '*') {
                in.skip();
                String num = "";
                while (in.hasNext() && ((in.peek() >= '0' && in.peek() <= '9'))) {
                    num += in.next();
                }
                long l = Long.parseLong(num);
                char c = in.next();
                for (long i = 0; i < l; i++) {
                    s += c;
                }
            } else {
                s += in.next();
            }
        }
        in.skip();

        char[] finalS = s.toCharArray();
        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public void apply(Stack<NylonObject> stack) {
                stack.add(new NylonString(finalS));
            }

            @Override
            public String toString() {
                return "PushNylonString(\"" + new String(finalS).replaceAll("\\n", "\\\\n") + "\")";
            }
        });

        return true;
    }
}
