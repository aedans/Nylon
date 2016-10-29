package nylon.parser;

import javafx.util.Pair;
import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.functions.*;
import nylon.functions.loops.WhileLoop;
import nylon.objects.NylonCharacter;
import nylon.objects.NylonDouble;
import nylon.objects.NylonFunction;
import nylon.objects.NylonString;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public final class FunctionParser {

    public static LinkedList<NylonFunction> parse(NylonRuntime runtime, String src) throws NylonRuntimeException {
        LinkedList<NylonFunction> functions = new LinkedList<>();
        for (int i = 0; i < src.length(); i++) {
            Pair<Integer, NylonFunction> pair = parseFunction(runtime, src, i);
            if (pair.getValue() != null)
                functions.add(pair.getValue());
            i = pair.getKey();
        }
        return functions;
    }

    private static Pair<Integer, NylonFunction> parseFunction(NylonRuntime runtime, String src, int i)
            throws NylonRuntimeException {
        if (Character.isDigit(src.charAt(i))) {
            int j = i;
            for (; i < src.length(); i++) {
                if (!Character.isDigit(src.charAt(i)))
                    break;
            }
            return new Pair<>(
                    i-1, new PushNylonObjectFunction<>(new NylonDouble(Double.parseDouble(src.substring(j, i))))
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
                    i, new NylonSrcFunction(runtime, src.substring(j, i), -2)
            );
        }
        if (src.charAt(i) == '(') {
            int depth = 1, j = i + 1;
            for (i = j; i < src.length(); i++) {
                if (src.charAt(i) == '(')
                    depth++;
                if (src.charAt(i) == ')')
                    depth--;
                if (depth == 0)
                    break;
            }
            if (depth == 0){
                return new Pair<>(
                        i, new WhileLoop(new NylonSrcFunction(runtime, src.substring(j, i), -2))
                );
            } else {
                Pair<Integer, NylonFunction> pair = parseFunction(runtime, src, j);
                return new Pair<>(
                        pair.getKey(), new WhileLoop(pair.getValue())
                );
            }
        }
        if (src.charAt(i) == '@') {
            Pair<Integer, NylonFunction> pair = parseFunction(runtime, src, i + 1);
            return new Pair<>(
                    pair.getKey(), new PushNylonObjectFunction<>(pair.getValue())
            );
        }
        return new Pair<>(
                i, runtime.getFunctionDictionary().get(runtime, src.charAt(i))
        );
    }

    public static int getArgs(char c) {
        if (c >= 30 && c < 40)
            return c-30;
        if (c == '.')
            return -1;
        if (c == ',')
            return -2;
        return 0;
    }

}