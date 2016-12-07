package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonStack;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class DoubleParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || !(in.peek() >= '0' && in.peek() <= '9'))
            return false;

        double d = Double.parseDouble(in.until(
                sin -> sin.hasNext() && ((sin.peek() >= '0' && sin.peek() <= '9') || sin.peek() == '.' || sin.peek() == 'E')
        ));

        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.push(new NylonDouble(d));
                return stack.peek().toStack(stack);
            }

            @Override
            public String toString() {
                return "PushNylonDouble(" + d + ")";
            }
        });

        return true;
    }
}
