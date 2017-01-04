package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonCharacter;
import nylon.nylonobjects.NylonFunction;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Stack;

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
            public void applyImpl(Stack<NylonObject> stack) {
                NylonCharacter nc = new NylonCharacter(c);
                stack.push(nc);
            }

            @Override
            public String toString() {
                return "PushNylonCharacter('" + (c == '\n' ? "\\n" : c) + "')";
            }
        });

        return true;
    }
}
