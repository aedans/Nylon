package nylon.parser.parsers;

import nylon.InlineFunction;
import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.LibraryFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;
import parser.ParseException;
import parser.Parser;
import parser.StringIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public class LibraryParser implements Parser<StringIterator, InlineFunction> {
    public static HashMap<String, Supplier<NylonFunction>> files = new HashMap<>();

    public static void build(File file, String s) {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                build(f, s + f.getName());
            } else {
                if (!LibraryParser.files.containsKey(s + f.getName()))
                    LibraryParser.files.put(s + f.getName(), new Supplier<NylonFunction>() {
                        private LibraryFunction function = null;

                        @Override
                        public LibraryFunction get() {
                            if (function == null) {
                                try {
                                    final StringBuilder content = new StringBuilder();
                                    new BufferedReader(new FileReader(f)).lines().forEach(s -> {
                                        content.append(s);
                                        content.append('\n');
                                    });
                                    InlineFunction inlineFunction = NylonParser.parse(s + f.getName(), content.toString());
                                    return function = new LibraryFunction(s + f.getName()) {
                                        @Override
                                        public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                                            inlineFunction.apply(stack);
                                        }

                                        @Override
                                        public String toString() {
                                            return super.toString() + inlineFunction.toString().substring(14);
                                        }
                                    };
                                } catch (IOException e) {
                                    throw new RuntimeException(e.getMessage());
                                }
                            } else {
                                return function;
                            }
                        }
                    });
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

            Supplier<NylonFunction> function = files.get(name + ".nl");
            if (function == null)
                throw new ParseException("Could not find STDL Function with name \"" + name + "\"", this);

            inlineFunction.functions.add(function.get());
            return true;
        } catch (ParseException e) {
            throw new ParseException(e.getMessage(), this);
        }
    }
}
