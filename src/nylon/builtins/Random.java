package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonLong;
import nylon.parser.parsers.LibraryParser;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class Random {
    public static void build() {
        LibraryParser.files.put("Rd.nl", () -> new LibraryFunction("Rd.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.push(new NylonDouble(new java.util.Random().nextDouble()));
            }
        });
        LibraryParser.files.put("Rl.nl", () -> new LibraryFunction("Rl.nl") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.push(new NylonLong(new java.util.Random().nextLong()));
            }
        });
    }
}
