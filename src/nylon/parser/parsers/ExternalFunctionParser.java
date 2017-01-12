package nylon.parser.parsers;

import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public class ExternalFunctionParser {
    public static void addTo(ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers) {
        for (int i = 'a'; i <= 'z'; i++) {
            parsers.set(i, ExternalFunctionParser::parse);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            parsers.set(i, ExternalFunctionParser::parse);
        }
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
            String name = "";
            while (in.hasNext()) {
                if (in.isInRange('a', 'z')) {
                    name += in.next();
                    break;
                } else if (in.isInRange('A', 'Z') || in.peek() == '_' || in.peek() == '_') {
                    name += in.next();
                } else {
                    break;
                }
            }

            Supplier<NylonFunction> function = nylonParser.functions.get(name);
            if (function == null)
                throw new RuntimeException("Could not find function with name \"" + name + "\"");

        return function.get();
    }
}
