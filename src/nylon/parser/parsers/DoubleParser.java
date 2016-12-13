package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonFunction;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class DoubleParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || !(in.peek() >= '0' && in.peek() <= '9'))
            return false;

        String s = "";
        while (in.hasNext() && ((in.peek() >= '0' && in.peek() <= '9') || in.peek() == '.' || in.peek() == 'E')) {
            s += in.next();
        }
        double d = Double.parseDouble(s);

        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                NylonDouble nd = new NylonDouble(d);
                stack.push(nd);
                return nd;
            }

            @Override
            public String toString() {
                return "PushNylonDouble(\"" + d + "\")";
            }
        });

        return true;
    }
}
