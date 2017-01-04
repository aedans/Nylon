package nylon;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class NylonException extends Exception {
    private ArrayList<NylonObject> causes = new ArrayList<>();
    private String message;

    public NylonException(String message, NylonObject source) {
        this.message = message;
        add(source);
    }

    public void add(NylonObject source) {
        causes.add(source);
    }

    @Override
    public String getMessage() {
        return toString();
    }

    @Override
    public String toString() {
        String s = message;
        for (NylonObject object : causes) {
            s += "\n\tat " + object.id;
        }
        return s;
    }
}
