package nylon;

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
        String n = "";
        for (String string : strings) {
            n += string + " ";
        }
        long t = System.nanoTime();
        this.main = NylonParser.parse(n + src);
        System.out.printf("Program compiled in %f milliseconds\n", (double) (System.nanoTime() - t) / 1000000d);
    }

    @Override
    public void run() {
        this.main.apply(nylonStack);
        for (NylonObject nylonObject : nylonStack) {
            System.out.print(nylonObject);
        }
    }
}
