package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonFile;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.parsers.LibraryParser;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class FileLibrary {
    public static void build() {
        LibraryParser.files.put("Fc.nl", () -> new NylonFunction() {
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
        LibraryParser.files.put("Fd.nl", () -> new NylonFunction() {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                File file = new File(stack.peek().toString());
                file.mkdir();
                return new NylonFile(file);
            }
        });
        LibraryParser.files.put("Fn.nl", () -> new NylonFunction() {
            @Override
            public NylonObject apply(Stack<NylonObject> stack) {
                stack.add(new NylonFile(new File(stack.pop().toString())));
                return stack.peek();
            }
        });
    }
}
