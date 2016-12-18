package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonList;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class WhileLoopParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '&')
            return false;
        in.skip();

        NylonFunction test = NylonParser.parse(in);
        NylonFunction wrapped = NylonParser.parse(in);

        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                NylonList ret = new NylonList();
                while (test.apply(stack).toBoolean(stack)) {
                    ret.add(wrapped.apply(stack));
                }
                return ret;
            }

            @Override
            public String toString() {
                return "While[" + test + "]Do[" + wrapped + "]";
            }
        });

        return true;
    }
}
