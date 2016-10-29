import nylon.NylonRuntime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

/**
 * Created by Aedan Smith.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            System.out.println(
                    "Usage:\n" +
                            "    nylon [library-path] [filename]\n" +
                            "    nylon -c\n" +
                            "    nylond [filename]\n" +
                            "\n" +
                            "See more at github.com/aedans/Nylon"
            );
            System.exit(0);
        }

        if (Objects.equals(args[0], "-c")){
            for (int i = 32; i < 256; i++) {
                System.out.print((char) i);
                if ((i + 32) % 64 == 0 && i != 32)
                    System.out.println();
            }
        } else {
            final String[] content = {""};
            new BufferedReader(new FileReader(args[1])).lines().forEach(s -> content[0] += s + '\n');
            NylonRuntime nylonRuntime = new NylonRuntime(content[0], new File(args[0]));
            for (int i = 2; i < args.length; i++) {
                nylonRuntime.addInput(args[i]);
            }
            nylonRuntime.run();
        }
    }

}
