package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.LibraryFunction;
import nylon.builtins.objects.NylonFile;
import nylon.nylonobjects.NylonBoolean;
import nylon.nylonobjects.NylonFunction;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public final class FileLibrary {
    public static void build(HashMap<String, Supplier<NylonFunction>> functions) {
        functions.put("Fa", () -> new LibraryFunction("Fa.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                try {
                    String s = stack.pop().toString();
                    new PrintStream(Files.newOutputStream(Paths.get(stack.pop().toString()), StandardOpenOption.APPEND)).print(s);
                } catch (IOException e) {
                    throw new NylonException(e.getMessage(), this);
                }
            }
        });
        functions.put("Fc", () -> new LibraryFunction("Fc.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                try {
                    File file = new File(stack.peek().toString());
                    file.createNewFile();
                } catch (IOException e) {
                    throw new NylonException(e.getMessage(), this);
                }
            }
        });
        functions.put("Fd", () -> new LibraryFunction("Fd.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                File file = new File(stack.peek().toString());
                file.mkdir();
            }
        });
        functions.put("Ff", () -> new LibraryFunction("Ff.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                File file = new File(stack.peek().toString());
                stack.add(new NylonBoolean(file.isFile()));
            }
        });
        functions.put("Fn", () -> new LibraryFunction("Fn.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) {
                stack.add(new NylonFile(new File(stack.pop().toString())));
            }
        });
        functions.put("Fw", () -> new LibraryFunction("Fw.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                try {
                    String s = stack.pop().toString();
                    new PrintStream(Files.newOutputStream(Paths.get(stack.pop().toString()))).print(s);
                } catch (IOException e) {
                    throw new NylonException(e.getMessage(), this);
                }
            }
        });
    }
}
