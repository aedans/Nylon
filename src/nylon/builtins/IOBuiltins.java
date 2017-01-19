package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.BuiltinFunction;
import nylon.nylonobjects.NylonString;
import nylon.parser.Parser;
import nylon.parser.parsers.BuiltinParser;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class IOBuiltins {
    public static void build(ArrayList<Parser> parsers) {
        parsers.set('', BuiltinParser.getParser(new BuiltinFunction('') {
            Scanner scanner = new Scanner(System.in);

            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                stack.add(new NylonString(scanner.nextLine().toCharArray()));
            }
        }));
        parsers.set('ß', BuiltinParser.getParser(new BuiltinFunction('ß') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                System.out.println(stack.peek());
            }
        }));
    }
}
