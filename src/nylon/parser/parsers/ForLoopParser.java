package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonStack;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class ForLoopParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '~')
            return false;
        in.skip();

        InlineFunction wrapped = new InlineFunction();
        NylonParser.nylonParser.parse(wrapped, in);

        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonStack apply(NylonStack stack) {
                Iterator<NylonObject> iterator = stack.pop().toIterator(stack);
                NylonStack returnStack = new NylonStack();
                while (iterator.hasNext()) {
                    stack.push(iterator.next());
                    returnStack.addAll(wrapped.apply(stack));
                }
                return returnStack;
            }

            @Override
            public String toString() {
                return "ForLoop(" + wrapped + ")";
            }
        });

        return true;
    }
}
