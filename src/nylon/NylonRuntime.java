package nylon;

import nylon.nylonobjects.NylonCharacter;
import nylon.nylonobjects.NylonStack;
import nylon.parser.NylonParser;

import java.util.List;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime implements Runnable {
    private InlineFunction main;
    private NylonStack nylonStack = new NylonStack();

    public NylonRuntime(String src, List<String> strings) {
        this.main = NylonParser.parse(src);
        for (String arg : strings) {
            NylonStack string = new NylonStack();
            for (char c : arg.toCharArray()) {
                string.add(new NylonCharacter(c));
            }
            nylonStack.add(string);
        }
    }

    @Override
    public void run() {
        this.main.apply(nylonStack);
        if (nylonStack.size() != 0) {
            System.out.println(nylonStack.peek());
        }
    }
}
