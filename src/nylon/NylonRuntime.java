package nylon;

import nylon.nylonobjects.NylonCharacter;
import nylon.nylonobjects.NylonList;
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
        this.main = NylonParser.parse(src);
        for (String arg : strings) {
            NylonList string = new NylonList();
            for (char c : arg.toCharArray()) {
                string.add(new NylonCharacter(c));
            }
            nylonStack.add(string);
        }
    }

    @Override
    public void run() {
        this.main.apply(nylonStack);
        for (NylonObject nylonObject : nylonStack) {
            System.out.print(nylonObject);
        }
    }
}
