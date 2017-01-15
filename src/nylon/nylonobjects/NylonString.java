package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

// TODO: Type safety.
public class NylonString extends NylonArray {
    public NylonString(char[] s) {
        for (char c : s) {
            this.add(new NylonCharacter(c));
        }
        this.type = Type.STRING;
        this.id = "String";
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return Double.parseDouble(this.toString());
    }

    @Override
    public long toLong(Stack<NylonObject> stack) {
        return Long.parseLong(this.toString());
    }

    @Override
    public NylonString toNylonString(Stack<NylonObject> stack) {
        return this;
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
