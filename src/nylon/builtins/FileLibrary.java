package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFile;
import nylon.nylonobjects.NylonString;
import nylon.parser.parsers.LibraryParser;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class FileLibrary {
    public static void build() {
        LibraryParser.files.put("Fa.nl", () -> new LibraryFunction("Fa.nl") {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                try {
                    String s = stack.pop().toString();
                    new PrintStream(Files.newOutputStream(Paths.get(stack.pop().toString()), StandardOpenOption.APPEND)).print(s);
                    return new NylonString(s.toCharArray());
                } catch (IOException e) {
                    throw new NylonException(e.getMessage());
                }
            }
        });
        LibraryParser.files.put("Fc.nl", () -> new LibraryFunction("Fc.nl") {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                try {
                    File file = new File(stack.peek().toString());
                    file.createNewFile();
                    return new NylonFile(file);
                } catch (IOException e) {
                    throw new NylonException(e.getMessage());
                }
            }
        });
        LibraryParser.files.put("Fd.nl", () -> new LibraryFunction("Fd.nl") {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                File file = new File(stack.peek().toString());
                file.mkdir();
                return new NylonFile(file);
            }
        });
        LibraryParser.files.put("Fn.nl", () -> new LibraryFunction("Fn.nl") {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                stack.add(new NylonFile(new File(stack.pop().toString())));
                return stack.peek();
            }
        });
        LibraryParser.files.put("Fw.nl", () -> new LibraryFunction("Fw.nl") {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                try {
                    String s = stack.pop().toString();
                    new PrintStream(Files.newOutputStream(Paths.get(stack.pop().toString()))).print(s);
                    return new NylonString(s.toCharArray());
                } catch (IOException e) {
                    throw new NylonException(e.getMessage());
                }
            }
        });
    }
}
