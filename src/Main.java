import nylon.NylonObject;
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

        long t = System.nanoTime();
        NylonRuntime nylonRuntime = new NylonRuntime(
                args[1],
                content.toString(),
                new File(args[0]),
                Arrays.asList(args).subList(2, args.length)
        );
        System.out.printf("Program compiled in %f milliseconds\n", (double) (System.nanoTime() - t) / 1000000d);

        t = System.nanoTime();
        nylonRuntime.run();
        System.out.printf("Program ran in %f milliseconds\n\n", (double) (System.nanoTime() - t) / 1000000d);

        for (NylonObject nylonObject : nylonRuntime.nylonStack) {
            if (nylonObject.shouldOutputNewline) {
                System.out.println(nylonObject);
            } else {
                System.out.print(nylonObject);
            }
        }
        System.out.println();
    }
}
