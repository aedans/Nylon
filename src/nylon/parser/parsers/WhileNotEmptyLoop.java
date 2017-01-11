package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonException;
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

public class WhileNotEmptyLoop implements Parser<StringIterator, InlineFunction> {
    private NylonParser nylonParser;

    public WhileNotEmptyLoop(NylonParser nylonParser) {
        this.nylonParser = nylonParser;
    }

    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '&')
            return false;
        in.skip();

        NylonFunction wrapped = nylonParser.parse(in);

        inlineFunction.functions.add(new NylonFunction("WhileNotEmpty(" + wrapped.getId() + ")") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                while (!stack.isEmpty()) {
                    wrapped.apply(stack);
                }
            }
        });

        return true;
    }
}
