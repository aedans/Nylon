package nylon;

import nylon.nylonobjects.NylonString;
import nylon.parser.CharIterator;
import nylon.parser.NylonParser;

import java.io.File;
import java.util.List;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime {
    public Stack<NylonObject> nylonStack = new Stack<>();
    private InlineFunction main;

    public NylonRuntime(String name, CharIterator src, File stdl, List<String> strings) {
        this.main = new InlineFunction(name);
        new NylonParser(stdl).parse(src, main);
        for (String s : strings) {
            nylonStack.add(new NylonString(s.toCharArray()));
        }
    }

    public void run() throws NylonException {
        this.main.apply(nylonStack);
    }
}
