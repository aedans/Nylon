package nylon.parser.parsers;

import nylon.InlineFunction;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Objects;

/**
 * Created by Aedan Smith.
 */

public class CommentParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext(2) || !Objects.equals(in.peekString(2), "//"))
            return false;

        in.until('\n');
        return true;
    }
}
