package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonCharacter;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonStack;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Arrays;

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
            public NylonStack apply(NylonStack stack) {
                NylonStack string = new NylonStack();
                for (char c : finalS) {
                    string.add(new NylonCharacter(c));
                }
                stack.add(string);
                return string;
            }

            @Override
            public String toString() {
                return "PushNylonString(" + Arrays.toString(finalS) + ")";
            }
        });

        return true;
    }
}
