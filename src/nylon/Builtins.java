package nylon;

import nylon.builtins.*;
import nylon.builtins.Math;
import nylon.builtins.objects.LibraryFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.NylonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public final class Builtins {
    public static void build(NylonParser nylonParser, File stdl) {
        buildLibrary(nylonParser, stdl, "");
        Math.build();
        Stack.build();
        Functions.build();
        AsciiCanvas.build();

        Random.build(nylonParser.functions);
        FileLibrary.build(nylonParser.functions);
        Optimizations.build(nylonParser.functions);
    }

    private static void buildLibrary(NylonParser nylonParser, File stdl, String path) {
        for (File f : stdl.listFiles()) {
            if (f.isDirectory()) {
                buildLibrary(nylonParser, f, path + f.getName());
            } else {
                if (!nylonParser.functions.containsKey(path + f.getName())) {
                    String name = path + f.getName().substring(0, f.getName().length() - 3);
                    nylonParser.functions.put(
                            name,
                            new LibraryFunctionSupplier(nylonParser, f, path)
                    );
                }
            }
        }
    }

    private static class LibraryFunctionSupplier implements Supplier<NylonFunction> {
        private LibraryFunction function = null;
        private NylonParser nylonParser;
        private File file;
        private String path;

        LibraryFunctionSupplier(NylonParser nylonParser, File file, String path) {
            this.nylonParser = nylonParser;
            this.file = file;
            this.path = path;
        }

        @Override
        public LibraryFunction get() {
            if (function == null) {
                try {
                    final StringBuilder content = new StringBuilder();
                    new BufferedReader(new FileReader(file)).lines().forEach(s -> {
                        content.append(s);
                        content.append('\n');
                    });
                    InlineFunction inlineFunction = nylonParser.parse(
                            path + file.getName(), content.toString()
                    );
                    return function = new LibraryFunction(path + file.getName()) {
                        @Override
                        public void applyImpl(java.util.Stack<NylonObject> stack) throws NylonException {
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
    }
}
