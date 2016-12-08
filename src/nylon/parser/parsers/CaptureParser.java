package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class CaptureParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '@')
            return false;
        in.skip();

        InlineFunction inlineFunction1 = new InlineFunction();
        NylonParser.nylonParser.parse(inlineFunction1, in);
        inlineFunction.functions.add(stack -> {
            stack.addAll(inlineFunction1.functions);
            return inlineFunction1;
        });
        return true;
    }
}
