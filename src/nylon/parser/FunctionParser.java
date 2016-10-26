package nylon.parser;

import javafx.util.Pair;
import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.functions.*;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonLong;
import nylon.objects.NylonString;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public final class FunctionParser {

    public static LinkedList<NylonFunction> parse(NylonRuntime runtime, String src) throws NylonRuntimeException {
        LinkedList<NylonFunction> functions = new LinkedList<>();
        for (int i = 0; i < src.length(); i++) {
            Pair<Integer, NylonFunction> pair = parseNextFunction(runtime, src, i);
            functions.add(pair.getValue());
            i = pair.getKey();
        }
        return functions;
    }

    private static Pair<Integer, NylonFunction> parseNextFunction(NylonRuntime runtime, String src, int i)
            throws NylonRuntimeException {
        if (Character.isDigit(src.charAt(i))) {
            int j = i;
            for (; i < src.length(); i++) {
                if (!Character.isDigit(src.charAt(i)))
                    break;
            }
            return new Pair<>(
                    i-1, new PushNylonObjectFunction<>(new NylonLong(Long.parseLong(src.substring(j, i))))
            );
        }
        // If the character is [a-zA-Z]
        if ((src.charAt(i) >= 97 && src.charAt(i) <= 122) || (src.charAt(i) >= 65 && src.charAt(i) <= 90)) {
            int j = i;
            for (; i < src.length(); i++) {
                if (!Character.isAlphabetic(src.charAt(i)))
                    break;
            }
            if (i == j + 1) {
                return new Pair<>(
                        i-1, new PushNylonObjectFunction<>(new NylonCharacter(src.charAt(j)))
                );
            } else {
                return new Pair<>(
                        i-1, new PushNylonObjectFunction<>(new NylonString(src.substring(j, i)))
                );
            }
        }
        if (src.charAt(i) == '[') {
            int depth = 1, j = i + 1;
            for (i = j; i < src.length(); i++) {
                if (src.charAt(i) == '[')
                    depth++;
                if (src.charAt(i) == ']')
                    depth--;
                if (depth == 0)
                    break;
            }
            return new Pair<>(
                    i-1, new NylonSrcFunction(runtime, src.substring(j, i), -1)
            );
        }
        return new Pair<>(
                i, runtime.getFunctionDictionary().get(runtime, src.charAt(i))
        );
    }

}