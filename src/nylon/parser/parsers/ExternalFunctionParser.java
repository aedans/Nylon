package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public class ExternalFunctionParser implements Parser<StringIterator, InlineFunction> {
    private NylonParser nylonParser;

    public ExternalFunctionParser(NylonParser nylonParser) {
        this.nylonParser = nylonParser;
    }

    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        try {
            if (!in.hasNext() || !(in.isInRange('a', 'z') || in.isInRange('A', 'Z')))
                return false;

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
                throw new ParseException("Could not find function with name \"" + name + "\"", this);

            inlineFunction.functions.add(function.get());
            return true;
        } catch (ParseException e) {
            throw new ParseException(e.getMessage(), this);
        }
    }
}
