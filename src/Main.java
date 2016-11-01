import com.aedan.argparser.ArgumentParser;
import com.aedan.argparser.ParseResult;
import nylon.NylonRuntime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Aedan Smith.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.addFlag("c");
        argumentParser.addFlag("h");
        argumentParser.addFlag("help");
        argumentParser.addKey("s");
        argumentParser.addKey("f");
        argumentParser.addKey("l", "C:/nylon/stdl");
        ParseResult parseResult = argumentParser.parse(args);

        if (parseResult.isEmpty() || parseResult.isFlagPresent("h") || parseResult.isFlagPresent("help")) {
            help();
        }

        if (parseResult.isFlagPresent("c")) {
            for (int i = 32; i < 256; i++) {
                System.out.print((char) i);
                if ((i + 32) % 64 == 0 && i != 32)
                    System.out.println();
            }
            System.exit(0);
        }

        String src = parseResult.getKeyValue("s");
        File stdl = new File(parseResult.getKeyValue("l"));
        if (src != null) {
            NylonRuntime nylonRuntime = new NylonRuntime(src, stdl);
            nylonRuntime.run();
        } else {
            final String[] content = {""};
            new BufferedReader(new FileReader(parseResult.getKeyValue("f"))).lines().forEach(s -> content[0] += s + '\n');
            NylonRuntime nylonRuntime = new NylonRuntime(content[0], stdl);
            parseResult.getTrailingArgs().forEach(nylonRuntime::addInput);
            nylonRuntime.run();
        }
    }

    private static void help() {
        System.out.println(
                "Usage:\n" +
                        "\t-s [string]: Executes a string.\n" +
                        "\t-f [string]: Executes a file.\n" +
                        "\t-l [string]: Changes the standard library path (default C:/nylon/stdl).\n" +
                        "\t-c: Prints the extended ascii characters.\n" +
                        "\n" +
                        "See more at github.com/aedans/Nylon"
        );
        System.exit(0);
    }

}
