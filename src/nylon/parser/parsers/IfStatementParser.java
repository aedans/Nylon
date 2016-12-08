package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonStack;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Objects;

/**
 * Created by Aedan Smith.
 */

public class IfStatementParser implements Parser<StringIterator, InlineFunction> {
    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() ||
                !(in.peek() == '?'
                        || in.peek() == '¿'
                        || in.peek() == '>'
                        || in.peek() == '<'
                        || in.peek() == '='))
            return false;

        char[] ifs = in.until(stringIterator -> in.hasNext() &&
                (in.peek() == '?'
                        || in.peek() == '¿'
                        || in.peek() == '>'
                        || in.peek() == '<'
                        || in.peek() == '=')).toCharArray();

        InlineFunction ifTrue = new InlineFunction();
        NylonParser.nylonParser.parse(ifTrue, in);

        in.skipWhitespace();
        InlineFunction ifFalse = new InlineFunction();
        if (in.hasNext() && in.peek() == '!')
            NylonParser.nylonParser.parse(ifFalse, in);

        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public NylonStack apply(NylonStack stack) {
                boolean b = false;
                for (char c : ifs) {
                    switch (c) {
                        case '?':
                            b = stack.peek().toBoolean(stack);
                            break;
                        case '¿':
                            b = !stack.peek().toBoolean(stack);
                            break;
                        case '>':
                            b = stack.get(stack.size() - 2).toDouble(stack) > stack.get(stack.size() - 1).toDouble(stack);
                            break;
                        case '<':
                            b = stack.get(stack.size() - 2).toDouble(stack) < stack.get(stack.size() - 1).toDouble(stack);
                            break;
                        case '=':
                            b = Objects.equals(stack.get(stack.size() - 1), stack.get(stack.size() - 2));
                            break;
                    }
                    if (b)
                        break;
                }
                if (b) {
                    ifTrue.apply(stack);
                    return new NylonStack(new NylonDouble(1));
                } else {
                    ifFalse.apply(stack);
                    return new NylonStack(new NylonDouble(0));
                }
            }

            @Override
            public String toString() {
                return "If[" + new String(ifs) + "](" + ifTrue + ")Else(" + ifFalse + ")";
            }
        });

        return true;
    }
}
