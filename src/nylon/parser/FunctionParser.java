package nylon.parser;

import nylon.NylonRuntime;
import nylon.exceptions.NylonRuntimeException;
import nylon.functions.*;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public final class FunctionParser {

    public static LinkedList<NylonFunction> parse(NylonRuntime runtime, String src) throws NylonRuntimeException {
        LinkedList<NylonFunction> functions = new LinkedList<>();
        for (int i = 0; i < src.length(); i++) {
            if (Character.isDigit(src.charAt(i))){
                int j = i;
                for (; i < src.length(); i++) {
                    if (!Character.isDigit(src.charAt(i)))
                        break;
                }
                functions.add(new PushLongFunction(Integer.parseInt(src.substring(j, i))));
                i--;
                continue;
            }
            // If the character is [a-zA-Z]
            if ((src.charAt(i) >= 97 && src.charAt(i) <= 122) || (src.charAt(i) >= 65 && src.charAt(i) <= 90)){
                int j = i;
                for (; i < src.length(); i++) {
                    if (!Character.isAlphabetic(src.charAt(i)))
                        break;
                }
                if (i == j+1)
                    functions.add(new PushCharacterFunction(src.charAt(j)));
                else
                    functions.add(new PushStringFunction(src.substring(j, i)));
                i--;
                continue;
            }
            if (src.charAt(i) == '['){
                int depth = 1, j = i+1;
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
            functions.add(FunctionDictionary.get(runtime, src.charAt(i)));
        }
        return functions;
    }

}
