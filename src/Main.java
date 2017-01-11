import nylon.NylonRuntime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Created by Aedan Smith.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: Nylon [library-path] [file]");
            return;
        }

        final StringBuilder content = new StringBuilder();
        new BufferedReader(new FileReader(args[1])).lines().forEach(s -> content.append(s).append('\n'));
        new NylonRuntime(
                args[1],
                content.toString(),
                new File(args[0]),
                Arrays.asList(args).subList(2, args.length)
        ).run();
    }
}
