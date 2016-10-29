package nylon.parser;

import javafx.util.Pair;
import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.functions.InlineFunction;
import nylon.functions.loops.WhileLoop;
import nylon.functions.misc.PushNylonObjectFunction;
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
        src = src.replaceAll("(?s)/\\*.*?\\*/","");
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
        if (src.charAt(i) >= 48 && src.charAt(i) < 58) {
            int j = i;
            for (; i < src.length(); i++) {
                if (!(src.charAt(i) >= 48 && src.charAt(i) < 58) && src.charAt(i) != '.')
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
                if (!(src.charAt(i) >= 97 && src.charAt(i) <= 122 || src.charAt(i) >= 65 && src.charAt(i) <= 90))
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
                    i, new InlineFunction(runtime, src.substring(j, i))
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
                        i, new WhileLoop(new InlineFunction(runtime, src.substring(j, i)))
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
                i, runtime.getFunctionDictionary().functionHashMap.get(src.charAt(i))
        );
    }

}