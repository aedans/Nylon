package nylon;

import nylon.nylonobjects.NylonString;
import nylon.parser.NylonParser;
import nylon.parser.StringIterator;

import java.io.File;
import java.util.List;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime {
    public Stack<NylonObject> nylonStack = new Stack<>();
    private InlineFunction main;

    public NylonRuntime(String name, String src, File stdl, List<String> strings) {
        this.main = new InlineFunction(name);
        new NylonParser(stdl).parse(new StringIterator(src), main);
        for (String s : strings) {
            nylonStack.add(new NylonString(s.toCharArray()));
        }
    }

    public void run() throws NylonException {
        this.main.apply(nylonStack);
    }
}
