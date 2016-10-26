package nylon.parser;

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
            if (Character.isDigit(src.charAt(i))) {
                int j = i;
                for (; i < src.length(); i++) {
                    if (!Character.isDigit(src.charAt(i)))
                        break;
                }
                functions.add(new PushNylonObjectFunction<>(new NylonLong(Long.parseLong(src.substring(j, i)))));
                i--;
                continue;
            }
            // If the character is [a-zA-Z]
            if ((src.charAt(i) >= 97 && src.charAt(i) <= 122) || (src.charAt(i) >= 65 && src.charAt(i) <= 90)) {
                int j = i;
                for (; i < src.length(); i++) {
                    if (!Character.isAlphabetic(src.charAt(i)))
                        break;
                }
                if (i == j + 1)
                    functions.add(new PushNylonObjectFunction<>(new NylonCharacter(src.charAt(j))));
                else
                    functions.add(new PushNylonObjectFunction<>(new NylonString(src.substring(j, i))));
                i--;
                continue;
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
                functions.add(new NylonSrcFunction(runtime, src.substring(j, i), -1));
                continue;
            }
            functions.add(runtime.getFunctionDictionary().get(runtime, src.charAt(i)));
        }
        return functions;
    }

}