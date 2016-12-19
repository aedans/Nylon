package nylon;

import nylon.builtins.*;
import nylon.builtins.Math;

/**
 * Created by Aedan Smith.
 */

public final class Builtins {
    public static void build() {
        Math.build();
        Stack.build();
        FileLibrary.build();
        Functions.build();
        AsciiCanvas.build();
    }
}
