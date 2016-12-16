package nylon.nylonobjects;

import nylon.NylonObject;

/**
 * Created by Aedan Smith.
 */

public class NylonString extends NylonList {
    public NylonString(char[] s) {
        for (char c : s) {
            this.add(new NylonCharacter(c));
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (NylonObject object : this) {
            s += object.toString();
        }
        return s;
    }

    @Override
    public NylonObject clone() {
        return new NylonString(toString().toCharArray());
    }
}
