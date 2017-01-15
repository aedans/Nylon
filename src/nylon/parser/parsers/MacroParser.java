package nylon.parser.parsers;

import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import nylon.parser.Parser;
import nylon.parser.StringIterator;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class MacroParser {
    public static void addTo(ArrayList<Parser> parsers) {
        parsers.set('#', MacroParser::parse);
    }

    public static NylonFunction parse(StringIterator in, NylonParser nylonParser) {
        in.skip();

        String name = "";
        while (in.hasNext()) {
            if (in.isInRange('a', 'z')
                    || in.isInRange('A', 'Z')
                    || in.isInRange('0', '9')
                    || in.peek() == '_'
                    || in.peek() == '-') {
                name += in.next();
            } else {
                break;
            }
        }

        NylonFunction nylonFunction = nylonParser.parse(in);

        nylonParser.functions.put(name, () -> nylonFunction);

        return null;
    }
}
