package nylon.parser.parsers;

import nylon.InlineFunction;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class CommentParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext(2) || !(in.peek() == '/' && in.peek(1) == '/'))
            return false;

        in.until('\n');
        return true;
    }
}
