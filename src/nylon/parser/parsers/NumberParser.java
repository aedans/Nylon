package nylon.parser.parsers;

import nylon.NylonObject;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonLong;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Created by Aedan Smith.
 */

public class NumberParser {
    public static void addTo(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
        for (int i = '0'; i <= '9'; i++) {
            parsers.set(i, NumberParser::parse);
        }
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        boolean isDouble = false;
        String s = "";
        while (in.hasNext() && ((in.peek() >= '0' && in.peek() <= '9') || in.peek() == '.' || in.peek() == 'E')) {
            if (in.peek() == '.')
                isDouble = true;
            s += in.next();
        }
        if (isDouble) {
            double d = Double.parseDouble(s);

            return new NylonFunction("PushNylonDouble(\"" + d + "\")") {
                @Override
                public void applyImpl(Stack<NylonObject> stack) {
                    NylonDouble nd = new NylonDouble(d);
                    stack.push(nd);
                }

                @Override
                public String toString() {
                    return id;
                }
            };
        } else {
            long l = Long.parseLong(s);

            return new NylonFunction("PushNylonLong(\"" + l + "\")") {
                @Override
                public void applyImpl(Stack<NylonObject> stack) {
                    NylonLong nl = new NylonLong(l);
                    stack.push(nl);
                }

                @Override
                public String toString() {
                    return id;
                }
            };
        }
    }
}
