package nylon.nylonobjects;

import nylon.NylonObject;

/**
 * Created by Aedan Smith.
 */

// TODO: Type safety.
public class NylonString extends NylonArray {
    public NylonString(char[] s) {
        for (char c : s) {
            this.value.add(new NylonCharacter(c));
        }
        this.type = Type.STRING;
        this.id = "String";
    }

    @Override
    public double toDouble() {
        return Double.parseDouble(this.toString());
    }

    @Override
    public long toLong() {
        return Long.parseLong(this.toString());
    }

    @Override
    public NylonString toNylonString() {
        return this;
    }

    @Override
    public String toString() {
        String s = "";
        for (NylonObject object : this.value) {
            s += object.toString();
        }
        return s;
    }

    @Override
    public NylonObject clone() {
        return new NylonString(toString().toCharArray());
    }
}
