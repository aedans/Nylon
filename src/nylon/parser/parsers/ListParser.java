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

public class ListParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '{')
            return false;
        in.skip();

        NylonList s = new NylonList();
        while (in.hasNext() && in.peek() != '}') {
            InlineFunction inlineFunction1 = new InlineFunction();
            NylonParser.nylonParser.parse(inlineFunction1, in);
            s.add(inlineFunction1.apply(new Stack<>()));
        }
        in.skip();

        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                stack.add(new NylonList(stack));
                return stack.peek().toList(stack);
            }

            @Override
            public String toString() {
                return "PushStackFunction(" + s + ")";
            }
        });

        return true;
    }
}
