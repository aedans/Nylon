package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonCharacter;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonStack;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class CharacterParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '\'')
            return false;
        in.skip();

        char c = in.next();

        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonObject apply(NylonStack stack) {
                NylonCharacter nc = new NylonCharacter(c);
                stack.push(nc);
                return nc;
            }

            @Override
            public String toString() {
                return "PushNylonCharacter(" + c + ")";
            }
        });

        return true;
    }
}
