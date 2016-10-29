import nylon.NylonRuntime;

import java.io.*;
import java.util.Objects;

/**
 * Created by Aedan Smith.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            System.out.println("Usage:\n\tnylon [filename] [library-path]\n\tnylon -c\n\nSee more at github.com/aedans/Nylon");
            System.exit(0);
        }

        if (Objects.equals(args[0], "-c")){
            for (int i = 32; i < 256; i++) {
                System.out.print((char) i);
                if ((i+32)%64 == 0)
                    System.out.println();
            }
        } else {
            final String[] content = {""};
            new BufferedReader(new FileReader(args[0])).lines().forEach(s -> content[0] += s + '\n');
            NylonRuntime nylonRuntime = new NylonRuntime(content[0], new File(args[1]));
            for (int i = 2; i < args.length; i++) {
                nylonRuntime.addInput(args[i]);
            }
            nylonRuntime.run();
        }
    }

}
