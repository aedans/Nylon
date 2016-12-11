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
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public class LibraryParser implements Parser<StringIterator, InlineFunction> {
    public static HashMap<String, Supplier<InlineFunction>> files = new HashMap<>();

    public static void build(File file, String s) {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                build(f, s + f.getName());
            } else {
                LibraryParser.files.put(s + f.getName(), new Supplier<InlineFunction>() {
                    private InlineFunction function = null;

                    @Override
                    public InlineFunction get() {
                        if (function == null) {
                            try {
                                final StringBuilder content = new StringBuilder();
                                new BufferedReader(new FileReader(f)).lines().forEach(s -> {
                                    content.append(s);
                                    content.append('\n');
                                });
                                return function = NylonParser.parse(content.toString());
                            } catch (IOException e) {
                                throw new NylonException(e.getMessage());
                            }
                        } else
                            return function;
                    }
                });
            }
        }
    }

    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
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

        Supplier<InlineFunction> function = files.get(name + ".nl");
        if (function == null)
            throw new NylonException("Could not find STDL Function with name \"" + name + "\"", this, in.getIndex());

        inlineFunction.functions.add(function.get());
        return true;
    }
}
