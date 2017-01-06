package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonLong;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NumberParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || !(in.peek() >= '0' && in.peek() <= '9'))
            return false;

        boolean isDouble = false;
        String s = "";
        while (in.hasNext() && ((in.peek() >= '0' && in.peek() <= '9') || in.peek() == '.' || in.peek() == 'E')) {
            if (in.peek() == '.')
                isDouble = true;
            s += in.next();
        }
        if (isDouble) {
            double d = Double.parseDouble(s);

            inlineFunction.functions.add(new NylonFunction("PushNylonDouble(\"" + d + "\")") {
                @Override
                public void applyImpl(Stack<NylonObject> stack) {
                    NylonDouble nd = new NylonDouble(d);
                    stack.push(nd);
                }

                @Override
                public String toString() {
                    return id;
                }
            });
        } else {
            long l = Long.parseLong(s);

            inlineFunction.functions.add(new NylonFunction("PushNylonLong(\"" + l + "\")") {
                @Override
                public void applyImpl(Stack<NylonObject> stack) {
                    NylonLong nl = new NylonLong(l);
                    stack.push(nl);
                }

                @Override
                public String toString() {
                    return id;
                }
            });
        }

        return true;
    }
}
