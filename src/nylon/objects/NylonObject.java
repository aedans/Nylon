package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public interface NylonObject extends Cloneable<NylonObject>, java.lang.Cloneable, Iterable<NylonStack, NylonObject>,
        Comparable<NylonStack, NylonObject>, java.lang.Comparable<NylonObject> {

    @Override
    String toString();

    char toChar() throws NylonRuntimeException;

    int toInteger() throws NylonRuntimeException;

    double toDouble() throws NylonRuntimeException;

    boolean toBoolean() throws NylonRuntimeException;

    default NylonStack toStack() {
        return new NylonStack(this);
    }

    NylonObject increment() throws NylonRuntimeException;

    NylonObject decrement() throws NylonRuntimeException;

    NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException;

    default NylonObject cast(char c) throws NylonRuntimeException {
        switch (c){
            case 'c':
                return new NylonCharacter(this.toChar());
            case 'i':
                return new NylonDouble(this.toInteger());
            case 'd':
                return new NylonDouble(this.toDouble());
            case 'b':
                return new NylonCharacter(this.toBoolean() ? 't' : 'f');
            case 's':
                return new NylonString(this.toString());
            case 'l':
                return this.toStack();
            default:
                throw new NylonRuntimeException("Could not cast: No class found with identifier '" + c + "'");
        }
    }

    @Override
    default int compareTo(NylonObject o) {
        try {
            return this.compareTo(o, null);
        } catch (NylonRuntimeException e) {
            e.printStackTrace(System.out);
            System.exit(1);
            return -1;
        }
    }

}
