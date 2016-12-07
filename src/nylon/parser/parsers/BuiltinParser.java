package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonFunction;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.HashMap;

/**
 * Created by Aedan Smith.
 */

public class BuiltinParser implements Parser<StringIterator, InlineFunction> {
    public static HashMap<Character, NylonFunction> builtins = new HashMap<>();

    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext())
            return false;

        NylonFunction n = builtins.get(in.peek());
        if (n == null)
            return false;

        in.skip();
        inlineFunction.functions.add(n);
        return true;
    }
}
