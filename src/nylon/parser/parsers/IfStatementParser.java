package nylon.parser.parsers;

import nylon.FunctionWrapper;
import nylon.InlineFunction;
import nylon.nylonobjects.NylonDouble;
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
                        || in.peek() == '='
                        || in.peek() == '!'))
            return false;

        char[] ifs = in.until(stringIterator -> in.hasNext() &&
                (in.peek() == '?'
                        || in.peek() == '¿'
                        || in.peek() == '>'
                        || in.peek() == '<'
                        || in.peek() == '='
                        || in.peek() == '!')).toCharArray();

        InlineFunction wrapped = new InlineFunction();
        NylonParser.nylonParser.parse(wrapped, in);

        inlineFunction.functions.add(new FunctionWrapper(wrapped) {
            @Override
            public NylonStack apply(NylonStack stack) {
                for (char c : ifs) {
                    boolean b = false;
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
                        case '!':
                            b = !Objects.equals(stack.get(stack.size() - 1), stack.get(stack.size() - 2));
                            break;
                    }
                    if (b) {
                        this.wrappedFunction.apply(stack);
                        return new NylonDouble(1).toStack(stack);
                    }
                }
                return new NylonDouble(0).toStack(stack);
            }

            @Override
            public String toString() {
                return "If[" + new String(ifs) + "](" + wrappedFunction + ")";
            }
        });

        return true;
    }
}
