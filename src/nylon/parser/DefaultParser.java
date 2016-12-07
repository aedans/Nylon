package nylon.parser;

import nylon.InlineFunction;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

class DefaultParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext())
            return false;

        in.skip();
        return NylonParser.nylonParser.parse(inlineFunction, in);
    }
}
