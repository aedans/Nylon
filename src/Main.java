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
        try {
            Builtins.build();
            LibraryParser.build(new File(args[0]), "");

            final String[] content = {""};
            new BufferedReader(new FileReader(args[1])).lines().forEach(s -> content[0] += s + "\n");
            new NylonRuntime(content[0], Arrays.asList(args).subList(2, args.length)).run();
        } catch (NylonException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
}
