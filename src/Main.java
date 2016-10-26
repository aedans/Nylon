import nylon.NylonRuntime;

import java.io.*;
import java.util.Objects;

/**
 * Created by Aedan Smith.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            System.out.println("Usage:\n\tnylon -f [filename]\n\tnylon -c\n\nSee more at github.com/aedans/Nylon");
            System.exit(0);
        }

        if (Objects.equals(args[0], "-c")){
            for (int i = 32; i < 256; i++) {
                System.out.print((char) i);
                if ((i+32)%64 == 0)
                    System.out.println();
            }
        }

        if (Objects.equals(args[0], "-f") && args.length >= 2){
            final String[] content = {""};
            new BufferedReader(new FileReader(args[1])).lines().forEach(s -> content[0] += s + '\n');
            NylonRuntime nylonRuntime = new NylonRuntime(content[0].trim());
            for (int i = 2; i < args.length; i++) {
                nylonRuntime.addInput(args[i]);
            }
            nylonRuntime.run();
        }
    }

}
