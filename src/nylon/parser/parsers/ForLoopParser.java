package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
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

        NylonFunction wrapped = NylonParser.parse(in);

        inlineFunction.functions.add(new NylonFunction() {
            {
                id = "ForLoop(" + wrapped.id + ")";
            }

            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                Iterator<NylonObject> iterator = consume ? stack.pop().toIterator(stack) : stack.peek().toIterator(stack);
                while (iterator.hasNext()) {
                    if (push)
                        stack.push(iterator.next());
                    else
                        iterator.next();
                    wrapped.apply(stack);
                }
            }

            @Override
            public String toString() {
                return "ForLoop[" + wrapped + "]";
            }
        });

        return true;
    }
}
