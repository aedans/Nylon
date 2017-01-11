package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class MacroParser implements Parser<StringIterator, InlineFunction> {
    private NylonParser nylonParser;

    public MacroParser(NylonParser nylonParser) {
        this.nylonParser = nylonParser;
    }

    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        if (!in.hasNext() || in.peek() != '#')
            return false;
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

        return true;
    }
}
