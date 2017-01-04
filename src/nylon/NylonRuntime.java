package nylon;

import nylon.nylonobjects.NylonString;
import nylon.parser.NylonParser;

import java.util.List;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime {
    private InlineFunction main;
    private Stack<NylonObject> nylonStack = new Stack<>();

    public NylonRuntime(String name, String src, List<String> strings) {
        long t = System.nanoTime();
        this.main = NylonParser.parse(name, src);
        System.out.printf("Program compiled in %f milliseconds\n", (double) (System.nanoTime() - t) / 1000000d);
        for (String s : strings) {
            nylonStack.add(new NylonString(s.toCharArray()));
        }
    }

    public void run() throws NylonException {
        long t = System.nanoTime();
        this.main.apply(nylonStack);
        for (NylonObject nylonObject : nylonStack) {
            if (nylonObject.shouldOutputNewline) {
                System.out.println(nylonObject);
            } else {
                System.out.print(nylonObject);
            }
        }
        System.out.printf("\nProgram ran in %f milliseconds\n", (double) (System.nanoTime() - t) / 1000000d);
    }
}
