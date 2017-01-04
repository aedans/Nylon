package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class NylonFunctionParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || (in.peek() != '[' && in.peek() != ']'))
            return false;
        if (in.peek() == ']')
            return true;
        in.skip();

        InlineFunction function = new InlineFunction("LambdaFunction");
        NylonParser.nylonParser.parseUntil(in, function, sin -> sin.hasNext() && sin.peek() != ']');
        inlineFunction.functions.add(function);

        in.skip();

        return true;
    }
}
