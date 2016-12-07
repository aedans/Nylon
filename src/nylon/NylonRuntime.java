package nylon;

import nylon.nylonobjects.NylonStack;
import nylon.parser.NylonParser;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime implements Runnable {
    private InlineFunction main;
    private NylonStack nylonStack = new NylonStack();

    public NylonRuntime(String src) {
        this.main = NylonParser.parse(src);
    }

    @Override
    public void run() {
        this.main.apply(nylonStack);
        System.out.println(nylonStack);
    }
}
