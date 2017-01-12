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
    private NylonParser nylonParser;
    private InlineFunction main;
    private Stack<NylonObject> nylonStack = new Stack<>();

    public NylonRuntime(String name, String src, File stdl, List<String> strings) {
        nylonParser = new NylonParser(stdl);
        long t = System.nanoTime();
        this.main = new InlineFunction(name);
        nylonParser.parse(new StringIterator(src), main);
        System.out.printf("Program compiled in %f milliseconds\n", (double) (System.nanoTime() - t) / 1000000d);
        for (String s : strings) {
            nylonStack.add(new NylonString(s.toCharArray()));
        }
    }

    public void run() throws NylonException {
        long t = System.nanoTime();
        this.main.apply(nylonStack);
        System.out.printf("Program ran in %f milliseconds\n\n", (double) (System.nanoTime() - t) / 1000000d);
        for (NylonObject nylonObject : nylonStack) {
            if (nylonObject.shouldOutputNewline) {
                System.out.println(nylonObject);
            } else {
                System.out.print(nylonObject);
            }
        }
        System.out.println();
    }
}
