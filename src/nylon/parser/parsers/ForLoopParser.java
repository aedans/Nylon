package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonList;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class ForLoopParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || !(in.peek() >= 'ì' && in.peek() <= 'ï'))
            return false;
        boolean push = in.peek() == 'î' || in.peek() == 'ì';
        boolean consume = in.peek() == 'î' || in.peek() == 'í';
        in.skip();

        InlineFunction wrapped = new InlineFunction();
        NylonParser.nylonParser.parse(wrapped, in);

        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                Iterator<NylonObject> iterator = consume ? stack.pop().toIterator(stack) : stack.peek().toIterator(stack);
                NylonList returnList = new NylonList();
                while (iterator.hasNext()) {
                    if (push)
                        stack.push(iterator.next());
                    else
                        iterator.next();
                    returnList.addAll(wrapped.apply(stack).toList(stack));
                }
                return returnList;
            }

            @Override
            public String toString() {
                return "ForLoop(" + wrapped + ")";
            }
        });

        return true;
    }
}
