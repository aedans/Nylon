package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Aedan Smith.
 */

public class LibParser implements Parser<StringIterator, InlineFunction> {
    public static HashMap<String, File> files = new HashMap<>();

    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        try {
            if (!in.hasNext() || !(in.isInRange('a', 'z') || in.isInRange('A', 'Z')))
                return false;

            String name = "";
            while (in.hasNext() && (in.isInRange('a', 'z') || in.isInRange('A', 'Z'))) {
                name += in.next();
            }
            File f = files.get(name);

            final String[] content = {""};
            new BufferedReader(new FileReader(f)).lines().forEach(s -> content[0] += s);

            inlineFunction.functions.add(NylonParser.parse(content[0]));
            return true;
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), this);
        }
    }
}
