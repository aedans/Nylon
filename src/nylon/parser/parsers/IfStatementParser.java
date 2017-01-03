package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.Objects;
import java.util.Stack;

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

        NylonFunction ifTrue = NylonParser.parse(in);

        in.skipWhitespace();
        NylonFunction ifFalse = new InlineFunction();
        if (in.hasNext() && in.peek() == '!')
            ifFalse = NylonParser.parse(in);

        NylonFunction finalIfFalse = ifFalse;
        inlineFunction.functions.add(new NylonFunction() {
            @Override
            public void apply(Stack<NylonObject> stack) {
                boolean b = false;
                for (char c : ifs) {
                    switch (c) {
                        case '?':
                            b = stack.peek().toBoolean(stack);
                            break;
                        case '¿':
                            b = !stack.peek().toBoolean(stack);
                            break;
                        case '>': {
                            NylonObject o1 = stack.peek(), o2 = stack.get(stack.size() - 2);
                            b = o2.toDouble(stack) > o1.toDouble(stack);
                            break;
                        }
                        case '<': {
                            NylonObject o1 = stack.peek(), o2 = stack.get(stack.size() - 2);
                            b = o2.toDouble(stack) < o1.toDouble(stack);
                            break;
                        }
                        case '=': {
                            NylonObject o1 = stack.peek(), o2 = stack.get(stack.size() - 2);
                            b = Objects.equals(o1, o2);
                            break;
                        }
                    }
                    if (b)
                        break;
                }
                if (b) {
                    ifTrue.apply(stack);
                } else {
                    finalIfFalse.apply(stack);
                }
            }

            @Override
            public String toString() {
                return "If[" + new String(ifs) + "]Then[" + ifTrue + "]Else[" + finalIfFalse + "]";
            }
        });

        return true;
    }
}
