package nylon.parser.parsers;

import nylon.FunctionWrapper;
import nylon.InlineFunction;
import nylon.nylonobjects.NylonStack;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class WhileLoopParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '&')
            return false;
        in.skip();

        InlineFunction wrapped = new InlineFunction();
        NylonParser.nylonParser.parse(wrapped, in);

        inlineFunction.functions.add(new FunctionWrapper(wrapped) {
            @Override
            public NylonStack apply(NylonStack stack) {
                while (wrappedFunction.apply(stack).toBoolean(stack)) {
                }
                return null;
            }

            @Override
            public String toString() {
                return "WhileLoop(" + wrappedFunction + ")";
            }
        });

        return true;
    }
}
