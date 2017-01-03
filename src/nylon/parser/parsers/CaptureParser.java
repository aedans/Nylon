package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class CaptureParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '@')
            return false;
        in.skip();

        NylonFunction capture = NylonParser.parse(in);
        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public void apply(Stack<NylonObject> stack) {
                stack.add(capture);
            }

            @Override
            public String toString() {
                return format("Capture[" + capture + "]");
            }
        });
        return true;
    }
}
