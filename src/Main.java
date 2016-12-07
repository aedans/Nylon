import nylon.NylonRuntime;
import nylon.builtins.Builtins;
import nylon.parser.parsers.LibParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Aedan Smith.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Builtins.build();
        LibParser.build(new File(args[0]), "");
        final String[] content = {""};
        new BufferedReader(new FileReader(args[1])).lines().forEach(s -> content[0] += s);
        new NylonRuntime(content[0]).run();
    }
}
