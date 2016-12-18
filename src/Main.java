import nylon.NylonException;
import nylon.NylonRuntime;
import nylon.builtins.Builtins;
import nylon.parser.parsers.LibraryParser;

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

        try {
            Builtins.build();
            LibraryParser.build(new File(args[0]), "");

            final StringBuilder content = new StringBuilder();
            new BufferedReader(new FileReader(args[1])).lines().forEach(s -> content.append(s).append('\n'));
            new NylonRuntime(content.toString(), Arrays.asList(args).subList(2, args.length)).run();
        } catch (NylonException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
}
