package nylon;

import nylon.nylonobjects.NylonDouble;
import nylon.nylonobjects.NylonLong;
import nylon.nylonobjects.NylonString;
import nylon.parser.NylonParser;

import java.util.List;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime implements Runnable {
    private InlineFunction main;
    private Stack<NylonObject> nylonStack = new Stack<>();

    public NylonRuntime(String src, List<String> strings) {
        for (String s : strings) {
            nylonStack.add(new NylonString(s.toCharArray()));
        }
        long t = System.nanoTime();
        this.main = NylonParser.parse(src);
        System.out.printf("Program compiled in %f milliseconds\n", (double) (System.nanoTime() - t) / 1000000d);
    }

    @Override
    public void run() {
        long t = System.nanoTime();
        this.main.apply(nylonStack);
        for (NylonObject nylonObject : nylonStack) {
            if (nylonObject instanceof NylonDouble || nylonObject instanceof NylonLong) {
                System.out.println(nylonObject);
            } else {
                System.out.print(nylonObject);
            }
        }
        System.out.printf("\nProgram ran in %f milliseconds\n", (double) (System.nanoTime() - t) / 1000000d);
    }
}
