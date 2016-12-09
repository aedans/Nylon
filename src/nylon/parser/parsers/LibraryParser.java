package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonException;
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

public class LibraryParser implements Parser<StringIterator, InlineFunction> {
    public static HashMap<String, File> files = new HashMap<>();

    public static void build(File file, String s) {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                build(f, s + f.getName());
            } else {
                LibraryParser.files.put(s + f.getName(), f);
            }
        }
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
                } else if (in.isInRange('A', 'Z')) {
                    name += in.next();
                } else {
                    break;
                }
            }
            File f = files.get(name + ".nl");
            if (f == null)
                throw new NylonException("Could not find STD Function \"" + name + "\"", this, in.getIndex());

            final String[] content = {""};
            new BufferedReader(new FileReader(f)).lines().forEach(s -> content[0] += s + "\n");

            inlineFunction.functions.add(NylonParser.parse(content[0]));
            return true;
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), this);
        }
    }
}
