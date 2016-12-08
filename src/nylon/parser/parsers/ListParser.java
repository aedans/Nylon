package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonStack;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class ListParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '{')
            return false;
        in.skip();

        NylonStack s = new NylonStack();
        while (in.hasNext() && in.peek() != '}') {
            InlineFunction inlineFunction1 = new InlineFunction();
            NylonParser.nylonParser.parse(inlineFunction1, in);
            s.addAll(inlineFunction1.apply(new NylonStack()));
        }
        in.skip();

        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonStack apply(NylonStack stack) {
                stack.add((NylonStack) s.clone());
                return stack.peek().toStack(stack);
            }

            @Override
            public String toString() {
                return "PushStackFunction(" + s + ")";
            }
        });

        return true;
    }
}
