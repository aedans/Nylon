package nylon.parser;

/**
 * Created by Aedan Smith.
 */

public class ParseUtils {
    public static String parseNextName(CharIterator in) {
        String name = "";
        while (in.hasNext()) {
            if (in.isInRange('A', 'Z')
                    || in.peek() == '_'
                    || in.peek() == '-') {
                name += in.next();
            } else if (in.isInRange('a', 'z')) {
                name += in.next();
                break;
            } else {
                break;
            }
        }
        return name;
    }
}
